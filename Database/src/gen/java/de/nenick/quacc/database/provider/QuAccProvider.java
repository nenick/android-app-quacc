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
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;

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

    private static final int URI_TYPE_ACCOUNTING_TEMPLATE = 4;
    private static final int URI_TYPE_ACCOUNTING_TEMPLATE_ID = 5;

    private static final int URI_TYPE_CATEGORY = 6;
    private static final int URI_TYPE_CATEGORY_ID = 7;

    private static final int URI_TYPE_INTERVAL = 8;
    private static final int URI_TYPE_INTERVAL_ID = 9;

    private static final int URI_TYPE_INTERVAL_ACCOUNTING = 10;
    private static final int URI_TYPE_INTERVAL_ACCOUNTING_ID = 11;

    private static final int URI_TYPE_TEMPLATE_MATCHING = 12;
    private static final int URI_TYPE_TEMPLATE_MATCHING_ID = 13;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME, URI_TYPE_ACCOUNT);
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNT_ID);
        URI_MATCHER.addURI(AUTHORITY, AccountingColumns.TABLE_NAME, URI_TYPE_ACCOUNTING);
        URI_MATCHER.addURI(AUTHORITY, AccountingColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNTING_ID);
        URI_MATCHER.addURI(AUTHORITY, AccountingTemplateColumns.TABLE_NAME, URI_TYPE_ACCOUNTING_TEMPLATE);
        URI_MATCHER.addURI(AUTHORITY, AccountingTemplateColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNTING_TEMPLATE_ID);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME, URI_TYPE_CATEGORY);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME + "/#", URI_TYPE_CATEGORY_ID);
        URI_MATCHER.addURI(AUTHORITY, IntervalColumns.TABLE_NAME, URI_TYPE_INTERVAL);
        URI_MATCHER.addURI(AUTHORITY, IntervalColumns.TABLE_NAME + "/#", URI_TYPE_INTERVAL_ID);
        URI_MATCHER.addURI(AUTHORITY, IntervalAccountingColumns.TABLE_NAME, URI_TYPE_INTERVAL_ACCOUNTING);
        URI_MATCHER.addURI(AUTHORITY, IntervalAccountingColumns.TABLE_NAME + "/#", URI_TYPE_INTERVAL_ACCOUNTING_ID);
        URI_MATCHER.addURI(AUTHORITY, TemplateMatchingColumns.TABLE_NAME, URI_TYPE_TEMPLATE_MATCHING);
        URI_MATCHER.addURI(AUTHORITY, TemplateMatchingColumns.TABLE_NAME + "/#", URI_TYPE_TEMPLATE_MATCHING_ID);
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

            case URI_TYPE_ACCOUNTING_TEMPLATE:
                return TYPE_CURSOR_DIR + AccountingTemplateColumns.TABLE_NAME;
            case URI_TYPE_ACCOUNTING_TEMPLATE_ID:
                return TYPE_CURSOR_ITEM + AccountingTemplateColumns.TABLE_NAME;

            case URI_TYPE_CATEGORY:
                return TYPE_CURSOR_DIR + CategoryColumns.TABLE_NAME;
            case URI_TYPE_CATEGORY_ID:
                return TYPE_CURSOR_ITEM + CategoryColumns.TABLE_NAME;

            case URI_TYPE_INTERVAL:
                return TYPE_CURSOR_DIR + IntervalColumns.TABLE_NAME;
            case URI_TYPE_INTERVAL_ID:
                return TYPE_CURSOR_ITEM + IntervalColumns.TABLE_NAME;

            case URI_TYPE_INTERVAL_ACCOUNTING:
                return TYPE_CURSOR_DIR + IntervalAccountingColumns.TABLE_NAME;
            case URI_TYPE_INTERVAL_ACCOUNTING_ID:
                return TYPE_CURSOR_ITEM + IntervalAccountingColumns.TABLE_NAME;

            case URI_TYPE_TEMPLATE_MATCHING:
                return TYPE_CURSOR_DIR + TemplateMatchingColumns.TABLE_NAME;
            case URI_TYPE_TEMPLATE_MATCHING_ID:
                return TYPE_CURSOR_ITEM + TemplateMatchingColumns.TABLE_NAME;

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
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + AccountingColumns.PREFIX_CATEGORY + " ON " + AccountingColumns.TABLE_NAME + "." + AccountingColumns.CATEGORY_ID + "=" + AccountingColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = AccountingColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_ACCOUNTING_TEMPLATE:
            case URI_TYPE_ACCOUNTING_TEMPLATE_ID:
                res.table = AccountingTemplateColumns.TABLE_NAME;
                res.idColumn = AccountingTemplateColumns._ID;
                res.tablesWithJoins = AccountingTemplateColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + AccountingTemplateColumns.PREFIX_ACCOUNT + " ON " + AccountingTemplateColumns.TABLE_NAME + "." + AccountingTemplateColumns.ACCOUNT_ID + "=" + AccountingTemplateColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + AccountingTemplateColumns.PREFIX_CATEGORY + " ON " + AccountingTemplateColumns.TABLE_NAME + "." + AccountingTemplateColumns.CATEGORY_ID + "=" + AccountingTemplateColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = AccountingTemplateColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CATEGORY:
            case URI_TYPE_CATEGORY_ID:
                res.table = CategoryColumns.TABLE_NAME;
                res.idColumn = CategoryColumns._ID;
                res.tablesWithJoins = CategoryColumns.TABLE_NAME;
                res.orderBy = CategoryColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_INTERVAL:
            case URI_TYPE_INTERVAL_ID:
                res.table = IntervalColumns.TABLE_NAME;
                res.idColumn = IntervalColumns._ID;
                res.tablesWithJoins = IntervalColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + IntervalColumns.PREFIX_ACCOUNT + " ON " + IntervalColumns.TABLE_NAME + "." + IntervalColumns.ACCOUNT_ID + "=" + IntervalColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + IntervalColumns.PREFIX_CATEGORY + " ON " + IntervalColumns.TABLE_NAME + "." + IntervalColumns.CATEGORY_ID + "=" + IntervalColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = IntervalColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_INTERVAL_ACCOUNTING:
            case URI_TYPE_INTERVAL_ACCOUNTING_ID:
                res.table = IntervalAccountingColumns.TABLE_NAME;
                res.idColumn = IntervalAccountingColumns._ID;
                res.tablesWithJoins = IntervalAccountingColumns.TABLE_NAME;
                if (IntervalColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + IntervalColumns.TABLE_NAME + " AS " + IntervalAccountingColumns.PREFIX_INTERVAL + " ON " + IntervalAccountingColumns.TABLE_NAME + "." + IntervalAccountingColumns.INTERVAL_ID + "=" + IntervalAccountingColumns.PREFIX_INTERVAL + "." + IntervalColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + IntervalColumns.PREFIX_ACCOUNT + " ON " + IntervalAccountingColumns.PREFIX_INTERVAL + "." + IntervalColumns.ACCOUNT_ID + "=" + IntervalColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + IntervalColumns.PREFIX_CATEGORY + " ON " + IntervalAccountingColumns.PREFIX_INTERVAL + "." + IntervalColumns.CATEGORY_ID + "=" + IntervalColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                if (AccountingColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountingColumns.TABLE_NAME + " AS " + IntervalAccountingColumns.PREFIX_ACCOUNTING + " ON " + IntervalAccountingColumns.TABLE_NAME + "." + IntervalAccountingColumns.ACCOUNTING_ID + "=" + IntervalAccountingColumns.PREFIX_ACCOUNTING + "." + AccountingColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + AccountingColumns.PREFIX_ACCOUNT + " ON " + IntervalAccountingColumns.PREFIX_ACCOUNTING + "." + AccountingColumns.ACCOUNT_ID + "=" + AccountingColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + AccountingColumns.PREFIX_CATEGORY + " ON " + IntervalAccountingColumns.PREFIX_ACCOUNTING + "." + AccountingColumns.CATEGORY_ID + "=" + AccountingColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = IntervalAccountingColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_TEMPLATE_MATCHING:
            case URI_TYPE_TEMPLATE_MATCHING_ID:
                res.table = TemplateMatchingColumns.TABLE_NAME;
                res.idColumn = TemplateMatchingColumns._ID;
                res.tablesWithJoins = TemplateMatchingColumns.TABLE_NAME;
                if (AccountingTemplateColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountingTemplateColumns.TABLE_NAME + " AS " + TemplateMatchingColumns.PREFIX_ACCOUNTING_TEMPLATE + " ON " + TemplateMatchingColumns.TABLE_NAME + "." + TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID + "=" + TemplateMatchingColumns.PREFIX_ACCOUNTING_TEMPLATE + "." + AccountingTemplateColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + AccountingTemplateColumns.PREFIX_ACCOUNT + " ON " + TemplateMatchingColumns.PREFIX_ACCOUNTING_TEMPLATE + "." + AccountingTemplateColumns.ACCOUNT_ID + "=" + AccountingTemplateColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + AccountingTemplateColumns.PREFIX_CATEGORY + " ON " + TemplateMatchingColumns.PREFIX_ACCOUNTING_TEMPLATE + "." + AccountingTemplateColumns.CATEGORY_ID + "=" + AccountingTemplateColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = TemplateMatchingColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_ACCOUNT_ID:
            case URI_TYPE_ACCOUNTING_ID:
            case URI_TYPE_ACCOUNTING_TEMPLATE_ID:
            case URI_TYPE_CATEGORY_ID:
            case URI_TYPE_INTERVAL_ID:
            case URI_TYPE_INTERVAL_ACCOUNTING_ID:
            case URI_TYPE_TEMPLATE_MATCHING_ID:
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
