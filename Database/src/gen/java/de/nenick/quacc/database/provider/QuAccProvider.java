package de.nenick.quacc.database.provider;

import java.util.Arrays;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import de.nenick.quacc.database.BuildConfig;
import de.nenick.quacc.database.provider.base.BaseContentProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryColumns;

public class QuAccProvider extends BaseContentProvider {
    private static final String TAG = QuAccProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "de.nenick.quacc.database.provider";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_ACCOUNT = 0;
    private static final int URI_TYPE_ACCOUNT_ID = 1;

    private static final int URI_TYPE_ACCOUNTING = 2;
    private static final int URI_TYPE_ACCOUNTING_ID = 3;

    private static final int URI_TYPE_ACCOUNTING_CATEGORY = 4;
    private static final int URI_TYPE_ACCOUNTING_CATEGORY_ID = 5;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME, URI_TYPE_ACCOUNT);
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNT_ID);
        URI_MATCHER.addURI(AUTHORITY, AccountingColumns.TABLE_NAME, URI_TYPE_ACCOUNTING);
        URI_MATCHER.addURI(AUTHORITY, AccountingColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNTING_ID);
        URI_MATCHER.addURI(AUTHORITY, AccountingCategoryColumns.TABLE_NAME, URI_TYPE_ACCOUNTING_CATEGORY);
        URI_MATCHER.addURI(AUTHORITY, AccountingCategoryColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNTING_CATEGORY_ID);
    }

    @Override
    protected SQLiteOpenHelper createSqLiteOpenHelper() {
        return QuAccSQLiteOpenHelper.getInstance(getContext());
    }

    @Override
    protected boolean hasDebug() {
        return DEBUG;
    }

    @Override
    public String getType(Uri uri) {
        int match = URI_MATCHER.match(uri);
        switch (match) {
            case URI_TYPE_ACCOUNT:
                return TYPE_CURSOR_DIR + AccountColumns.TABLE_NAME;
            case URI_TYPE_ACCOUNT_ID:
                return TYPE_CURSOR_ITEM + AccountColumns.TABLE_NAME;

            case URI_TYPE_ACCOUNTING:
                return TYPE_CURSOR_DIR + AccountingColumns.TABLE_NAME;
            case URI_TYPE_ACCOUNTING_ID:
                return TYPE_CURSOR_ITEM + AccountingColumns.TABLE_NAME;

            case URI_TYPE_ACCOUNTING_CATEGORY:
                return TYPE_CURSOR_DIR + AccountingCategoryColumns.TABLE_NAME;
            case URI_TYPE_ACCOUNTING_CATEGORY_ID:
                return TYPE_CURSOR_ITEM + AccountingCategoryColumns.TABLE_NAME;

        }
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (DEBUG) Log.d(TAG, "insert uri=" + uri + " values=" + values);
        return super.insert(uri, values);
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        if (DEBUG) Log.d(TAG, "bulkInsert uri=" + uri + " values.length=" + values.length);
        return super.bulkInsert(uri, values);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "update uri=" + uri + " values=" + values + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.update(uri, values, selection, selectionArgs);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        if (DEBUG) Log.d(TAG, "delete uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs));
        return super.delete(uri, selection, selectionArgs);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " selection=" + selection + " selectionArgs=" + Arrays.toString(selectionArgs) + " sortOrder=" + sortOrder
                    + " groupBy=" + uri.getQueryParameter(QUERY_GROUP_BY) + " having=" + uri.getQueryParameter(QUERY_HAVING) + " limit=" + uri.getQueryParameter(QUERY_LIMIT));
        return super.query(uri, projection, selection, selectionArgs, sortOrder);
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        QueryParams res = new QueryParams();
        String id = null;
        int matchedId = URI_MATCHER.match(uri);
        switch (matchedId) {
            case URI_TYPE_ACCOUNT:
            case URI_TYPE_ACCOUNT_ID:
                res.table = AccountColumns.TABLE_NAME;
                res.idColumn = AccountColumns._ID;
                res.tablesWithJoins = AccountColumns.TABLE_NAME;
                res.orderBy = AccountColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_ACCOUNTING:
            case URI_TYPE_ACCOUNTING_ID:
                res.table = AccountingColumns.TABLE_NAME;
                res.idColumn = AccountingColumns._ID;
                res.tablesWithJoins = AccountingColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + AccountingColumns.PREFIX_ACCOUNT + " ON " + AccountingColumns.TABLE_NAME + "." + AccountingColumns.ACCOUNT_ID + "=" + AccountingColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (AccountingCategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountingCategoryColumns.TABLE_NAME + " AS " + AccountingColumns.PREFIX_ACCOUNTING_CATEGORY + " ON " + AccountingColumns.TABLE_NAME + "." + AccountingColumns.ACCOUNTING_CATEGORY_ID + "=" + AccountingColumns.PREFIX_ACCOUNTING_CATEGORY + "." + AccountingCategoryColumns._ID;
                }
                res.orderBy = AccountingColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_ACCOUNTING_CATEGORY:
            case URI_TYPE_ACCOUNTING_CATEGORY_ID:
                res.table = AccountingCategoryColumns.TABLE_NAME;
                res.idColumn = AccountingCategoryColumns._ID;
                res.tablesWithJoins = AccountingCategoryColumns.TABLE_NAME;
                res.orderBy = AccountingCategoryColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_ACCOUNT_ID:
            case URI_TYPE_ACCOUNTING_ID:
            case URI_TYPE_ACCOUNTING_CATEGORY_ID:
                id = uri.getLastPathSegment();
        }
        if (id != null) {
            if (selection != null) {
                res.selection = res.table + "." + res.idColumn + "=" + id + " and (" + selection + ")";
            } else {
                res.selection = res.table + "." + res.idColumn + "=" + id;
            }
        } else {
            res.selection = selection;
        }
        return res;
    }
}
