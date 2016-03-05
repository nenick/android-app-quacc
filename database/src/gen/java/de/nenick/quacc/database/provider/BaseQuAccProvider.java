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
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeColumns;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;

public class BaseQuAccProvider extends BaseContentProvider {
    private static final String TAG = BaseQuAccProvider.class.getSimpleName();

    private static final boolean DEBUG = BuildConfig.DEBUG;

    private static final String TYPE_CURSOR_ITEM = "vnd.android.cursor.item/";
    private static final String TYPE_CURSOR_DIR = "vnd.android.cursor.dir/";

    public static final String AUTHORITY = "de.nenick.quacc";
    public static final String CONTENT_URI_BASE = "content://" + AUTHORITY;

    private static final int URI_TYPE_ACCOUNT = 0;
    private static final int URI_TYPE_ACCOUNT_ID = 1;

    private static final int URI_TYPE_BOOKING_ENTRY = 2;
    private static final int URI_TYPE_BOOKING_ENTRY_ID = 3;

    private static final int URI_TYPE_BOOKING_INTERVAL = 4;
    private static final int URI_TYPE_BOOKING_INTERVAL_ID = 5;

    private static final int URI_TYPE_BOOKING_INTERVAL_CHANGE = 6;
    private static final int URI_TYPE_BOOKING_INTERVAL_CHANGE_ID = 7;

    private static final int URI_TYPE_BOOKING_INTERVAL_ENTRY = 8;
    private static final int URI_TYPE_BOOKING_INTERVAL_ENTRY_ID = 9;

    private static final int URI_TYPE_BOOKING_TEMPLATE = 10;
    private static final int URI_TYPE_BOOKING_TEMPLATE_ID = 11;

    private static final int URI_TYPE_BOOKING_TEMPLATE_KEYWORD = 12;
    private static final int URI_TYPE_BOOKING_TEMPLATE_KEYWORD_ID = 13;

    private static final int URI_TYPE_CATEGORY = 14;
    private static final int URI_TYPE_CATEGORY_ID = 15;



    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME, URI_TYPE_ACCOUNT);
        URI_MATCHER.addURI(AUTHORITY, AccountColumns.TABLE_NAME + "/#", URI_TYPE_ACCOUNT_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingEntryColumns.TABLE_NAME, URI_TYPE_BOOKING_ENTRY);
        URI_MATCHER.addURI(AUTHORITY, BookingEntryColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_ENTRY_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalColumns.TABLE_NAME, URI_TYPE_BOOKING_INTERVAL);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_INTERVAL_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalChangeColumns.TABLE_NAME, URI_TYPE_BOOKING_INTERVAL_CHANGE);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalChangeColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_INTERVAL_CHANGE_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalEntryColumns.TABLE_NAME, URI_TYPE_BOOKING_INTERVAL_ENTRY);
        URI_MATCHER.addURI(AUTHORITY, BookingIntervalEntryColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_INTERVAL_ENTRY_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingTemplateColumns.TABLE_NAME, URI_TYPE_BOOKING_TEMPLATE);
        URI_MATCHER.addURI(AUTHORITY, BookingTemplateColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_TEMPLATE_ID);
        URI_MATCHER.addURI(AUTHORITY, BookingTemplateKeywordColumns.TABLE_NAME, URI_TYPE_BOOKING_TEMPLATE_KEYWORD);
        URI_MATCHER.addURI(AUTHORITY, BookingTemplateKeywordColumns.TABLE_NAME + "/#", URI_TYPE_BOOKING_TEMPLATE_KEYWORD_ID);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME, URI_TYPE_CATEGORY);
        URI_MATCHER.addURI(AUTHORITY, CategoryColumns.TABLE_NAME + "/#", URI_TYPE_CATEGORY_ID);
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

            case URI_TYPE_BOOKING_ENTRY:
                return TYPE_CURSOR_DIR + BookingEntryColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_ENTRY_ID:
                return TYPE_CURSOR_ITEM + BookingEntryColumns.TABLE_NAME;

            case URI_TYPE_BOOKING_INTERVAL:
                return TYPE_CURSOR_DIR + BookingIntervalColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_INTERVAL_ID:
                return TYPE_CURSOR_ITEM + BookingIntervalColumns.TABLE_NAME;

            case URI_TYPE_BOOKING_INTERVAL_CHANGE:
                return TYPE_CURSOR_DIR + BookingIntervalChangeColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_INTERVAL_CHANGE_ID:
                return TYPE_CURSOR_ITEM + BookingIntervalChangeColumns.TABLE_NAME;

            case URI_TYPE_BOOKING_INTERVAL_ENTRY:
                return TYPE_CURSOR_DIR + BookingIntervalEntryColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_INTERVAL_ENTRY_ID:
                return TYPE_CURSOR_ITEM + BookingIntervalEntryColumns.TABLE_NAME;

            case URI_TYPE_BOOKING_TEMPLATE:
                return TYPE_CURSOR_DIR + BookingTemplateColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_TEMPLATE_ID:
                return TYPE_CURSOR_ITEM + BookingTemplateColumns.TABLE_NAME;

            case URI_TYPE_BOOKING_TEMPLATE_KEYWORD:
                return TYPE_CURSOR_DIR + BookingTemplateKeywordColumns.TABLE_NAME;
            case URI_TYPE_BOOKING_TEMPLATE_KEYWORD_ID:
                return TYPE_CURSOR_ITEM + BookingTemplateKeywordColumns.TABLE_NAME;

            case URI_TYPE_CATEGORY:
                return TYPE_CURSOR_DIR + CategoryColumns.TABLE_NAME;
            case URI_TYPE_CATEGORY_ID:
                return TYPE_CURSOR_ITEM + CategoryColumns.TABLE_NAME;

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

            case URI_TYPE_BOOKING_ENTRY:
            case URI_TYPE_BOOKING_ENTRY_ID:
                res.table = BookingEntryColumns.TABLE_NAME;
                res.idColumn = BookingEntryColumns._ID;
                res.tablesWithJoins = BookingEntryColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingEntryColumns.PREFIX_ACCOUNT + " ON " + BookingEntryColumns.TABLE_NAME + "." + BookingEntryColumns.ACCOUNT_ID + "=" + BookingEntryColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingEntryColumns.PREFIX_CATEGORY + " ON " + BookingEntryColumns.TABLE_NAME + "." + BookingEntryColumns.CATEGORY_ID + "=" + BookingEntryColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingEntryColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_BOOKING_INTERVAL:
            case URI_TYPE_BOOKING_INTERVAL_ID:
                res.table = BookingIntervalColumns.TABLE_NAME;
                res.idColumn = BookingIntervalColumns._ID;
                res.tablesWithJoins = BookingIntervalColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_ACCOUNT + " ON " + BookingIntervalColumns.TABLE_NAME + "." + BookingIntervalColumns.ACCOUNT_ID + "=" + BookingIntervalColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_CATEGORY + " ON " + BookingIntervalColumns.TABLE_NAME + "." + BookingIntervalColumns.CATEGORY_ID + "=" + BookingIntervalColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingIntervalColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_BOOKING_INTERVAL_CHANGE:
            case URI_TYPE_BOOKING_INTERVAL_CHANGE_ID:
                res.table = BookingIntervalChangeColumns.TABLE_NAME;
                res.idColumn = BookingIntervalChangeColumns._ID;
                res.tablesWithJoins = BookingIntervalChangeColumns.TABLE_NAME;
                if (BookingIntervalColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + BookingIntervalColumns.TABLE_NAME + " AS " + BookingIntervalChangeColumns.PREFIX_BOOKING_INTERVAL + " ON " + BookingIntervalChangeColumns.TABLE_NAME + "." + BookingIntervalChangeColumns.BOOKING_INTERVAL_ID + "=" + BookingIntervalChangeColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_ACCOUNT + " ON " + BookingIntervalChangeColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns.ACCOUNT_ID + "=" + BookingIntervalColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_CATEGORY + " ON " + BookingIntervalChangeColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns.CATEGORY_ID + "=" + BookingIntervalColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingIntervalChangeColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_BOOKING_INTERVAL_ENTRY:
            case URI_TYPE_BOOKING_INTERVAL_ENTRY_ID:
                res.table = BookingIntervalEntryColumns.TABLE_NAME;
                res.idColumn = BookingIntervalEntryColumns._ID;
                res.tablesWithJoins = BookingIntervalEntryColumns.TABLE_NAME;
                if (BookingIntervalColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + BookingIntervalColumns.TABLE_NAME + " AS " + BookingIntervalEntryColumns.PREFIX_BOOKING_INTERVAL + " ON " + BookingIntervalEntryColumns.TABLE_NAME + "." + BookingIntervalEntryColumns.BOOKING_INTERVAL_ID + "=" + BookingIntervalEntryColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_ACCOUNT + " ON " + BookingIntervalEntryColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns.ACCOUNT_ID + "=" + BookingIntervalColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingIntervalColumns.PREFIX_CATEGORY + " ON " + BookingIntervalEntryColumns.PREFIX_BOOKING_INTERVAL + "." + BookingIntervalColumns.CATEGORY_ID + "=" + BookingIntervalColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                if (BookingEntryColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + BookingEntryColumns.TABLE_NAME + " AS " + BookingIntervalEntryColumns.PREFIX_BOOKING_ENTRY + " ON " + BookingIntervalEntryColumns.TABLE_NAME + "." + BookingIntervalEntryColumns.BOOKING_ENTRY_ID + "=" + BookingIntervalEntryColumns.PREFIX_BOOKING_ENTRY + "." + BookingEntryColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingEntryColumns.PREFIX_ACCOUNT + " ON " + BookingIntervalEntryColumns.PREFIX_BOOKING_ENTRY + "." + BookingEntryColumns.ACCOUNT_ID + "=" + BookingEntryColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingEntryColumns.PREFIX_CATEGORY + " ON " + BookingIntervalEntryColumns.PREFIX_BOOKING_ENTRY + "." + BookingEntryColumns.CATEGORY_ID + "=" + BookingEntryColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingIntervalEntryColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_BOOKING_TEMPLATE:
            case URI_TYPE_BOOKING_TEMPLATE_ID:
                res.table = BookingTemplateColumns.TABLE_NAME;
                res.idColumn = BookingTemplateColumns._ID;
                res.tablesWithJoins = BookingTemplateColumns.TABLE_NAME;
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingTemplateColumns.PREFIX_ACCOUNT + " ON " + BookingTemplateColumns.TABLE_NAME + "." + BookingTemplateColumns.ACCOUNT_ID + "=" + BookingTemplateColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingTemplateColumns.PREFIX_CATEGORY + " ON " + BookingTemplateColumns.TABLE_NAME + "." + BookingTemplateColumns.CATEGORY_ID + "=" + BookingTemplateColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingTemplateColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_BOOKING_TEMPLATE_KEYWORD:
            case URI_TYPE_BOOKING_TEMPLATE_KEYWORD_ID:
                res.table = BookingTemplateKeywordColumns.TABLE_NAME;
                res.idColumn = BookingTemplateKeywordColumns._ID;
                res.tablesWithJoins = BookingTemplateKeywordColumns.TABLE_NAME;
                if (BookingTemplateColumns.hasColumns(projection) || AccountColumns.hasColumns(projection) || CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + BookingTemplateColumns.TABLE_NAME + " AS " + BookingTemplateKeywordColumns.PREFIX_BOOKING_TEMPLATE + " ON " + BookingTemplateKeywordColumns.TABLE_NAME + "." + BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID + "=" + BookingTemplateKeywordColumns.PREFIX_BOOKING_TEMPLATE + "." + BookingTemplateColumns._ID;
                }
                if (AccountColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + AccountColumns.TABLE_NAME + " AS " + BookingTemplateColumns.PREFIX_ACCOUNT + " ON " + BookingTemplateKeywordColumns.PREFIX_BOOKING_TEMPLATE + "." + BookingTemplateColumns.ACCOUNT_ID + "=" + BookingTemplateColumns.PREFIX_ACCOUNT + "." + AccountColumns._ID;
                }
                if (CategoryColumns.hasColumns(projection)) {
                    res.tablesWithJoins += " LEFT OUTER JOIN " + CategoryColumns.TABLE_NAME + " AS " + BookingTemplateColumns.PREFIX_CATEGORY + " ON " + BookingTemplateKeywordColumns.PREFIX_BOOKING_TEMPLATE + "." + BookingTemplateColumns.CATEGORY_ID + "=" + BookingTemplateColumns.PREFIX_CATEGORY + "." + CategoryColumns._ID;
                }
                res.orderBy = BookingTemplateKeywordColumns.DEFAULT_ORDER;
                break;

            case URI_TYPE_CATEGORY:
            case URI_TYPE_CATEGORY_ID:
                res.table = CategoryColumns.TABLE_NAME;
                res.idColumn = CategoryColumns._ID;
                res.tablesWithJoins = CategoryColumns.TABLE_NAME;
                res.orderBy = CategoryColumns.DEFAULT_ORDER;
                break;

            default:
                throw new IllegalArgumentException("The uri '" + uri + "' is not supported by this ContentProvider");
        }

        switch (matchedId) {
            case URI_TYPE_ACCOUNT_ID:
            case URI_TYPE_BOOKING_ENTRY_ID:
            case URI_TYPE_BOOKING_INTERVAL_ID:
            case URI_TYPE_BOOKING_INTERVAL_CHANGE_ID:
            case URI_TYPE_BOOKING_INTERVAL_ENTRY_ID:
            case URI_TYPE_BOOKING_TEMPLATE_ID:
            case URI_TYPE_BOOKING_TEMPLATE_KEYWORD_ID:
            case URI_TYPE_CATEGORY_ID:
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
