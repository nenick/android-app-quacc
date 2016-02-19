package de.nenick.quacc.database;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import de.nenick.quacc.database.provider.BaseQuAccProvider;

public class QuAccProvider extends BaseQuAccProvider {

    public static final String TAG = "DatabaseStatistic";
    private static final boolean DEBUG = BuildConfig.DEBUG;

    @Override
    protected boolean hasDebug() {
        // avoid try to set "DEBUG_SQL_STATEMENTS", looks like this does not more work
        return false;
    }

    @Override
    protected QueryParams getQueryParams(Uri uri, String selection, String[] projection) {
        // workaround when query with content uri which contains id instead of providing a selection
        // error message: SELECT * FROM account WHERE account._id=1 and () ORDER BY account._id [near ")": syntax error]
        if (selection != null && selection.isEmpty()) {
            selection = null;
        }
        return super.getQueryParams(uri, selection, projection);
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // just add elapsed time logs
        long startTime = System.currentTimeMillis();
        Uri insert = super.insert(uri, values);
        if (DEBUG)
            Log.d(TAG, "insert uri=" + uri + " elapsed time=" + (System.currentTimeMillis() - startTime));
        return insert;
    }

    @Override
    public int bulkInsert(Uri uri, ContentValues[] values) {
        // just add elapsed time logs
        long startTime = System.currentTimeMillis();
        int count = super.bulkInsert(uri, values);
        if (DEBUG)
            Log.d(TAG, "bulkInsert uri=" + uri + " elapsed time=" + (System.currentTimeMillis() - startTime));
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        // just add elapsed time logs
        long startTime = System.currentTimeMillis();
        int count = super.update(uri, values, selection, selectionArgs);
        if (DEBUG)
            Log.d(TAG, "update uri=" + uri + " elapsed time=" + (System.currentTimeMillis() - startTime));
        return count;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // just add elapsed time logs
        long startTime = System.currentTimeMillis();
        int count = super.delete(uri, selection, selectionArgs);
        if (DEBUG)
            Log.d(TAG, "delete uri=" + uri + " elapsed time=" + (System.currentTimeMillis() - startTime));
        return count;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // just add elapsed time logs
        long startTime = System.currentTimeMillis();
        Cursor query = super.query(uri, projection, selection, selectionArgs, sortOrder);
        if (DEBUG)
            Log.d(TAG, "query uri=" + uri + " elapsed time=" + (System.currentTimeMillis() - startTime));
        return query;
    }
}
