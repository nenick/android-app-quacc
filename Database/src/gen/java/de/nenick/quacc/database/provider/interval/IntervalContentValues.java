package de.nenick.quacc.database.provider.interval;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code interval} table.
 */
public class IntervalContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return IntervalColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable IntervalSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public IntervalContentValues putAccountId(long value) {
        mContentValues.put(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }


    public IntervalContentValues putCategoryId(long value) {
        mContentValues.put(IntervalColumns.CATEGORY_ID, value);
        return this;
    }


    public IntervalContentValues putComment(@Nullable String value) {
        mContentValues.put(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalContentValues putCommentNull() {
        mContentValues.putNull(IntervalColumns.COMMENT);
        return this;
    }

    /**
     * The type of this interval how it will add accounting.
     */
    public IntervalContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(IntervalColumns.INTERVAL, value);
        return this;
    }


    /**
     * Start date when the interval have the first accounting.
     */
    public IntervalContentValues putDateStart(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateStart must not be null");
        mContentValues.put(IntervalColumns.DATE_START, value.getTime());
        return this;
    }


    public IntervalContentValues putDateStart(long value) {
        mContentValues.put(IntervalColumns.DATE_START, value);
        return this;
    }

    /**
     * Last date where the interval is active. This must not match the last accounting date.
     */
    public IntervalContentValues putDateEnd(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateEnd must not be null");
        mContentValues.put(IntervalColumns.DATE_END, value.getTime());
        return this;
    }


    public IntervalContentValues putDateEnd(long value) {
        mContentValues.put(IntervalColumns.DATE_END, value);
        return this;
    }

    /**
     * Until this date all accounting which belong to this interval are created or updated.
     */
    public IntervalContentValues putDateUpdatedUntil(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateUpdatedUntil must not be null");
        mContentValues.put(IntervalColumns.DATE_UPDATED_UNTIL, value.getTime());
        return this;
    }


    public IntervalContentValues putDateUpdatedUntil(long value) {
        mContentValues.put(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalContentValues putType(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("type must not be null");
        mContentValues.put(IntervalColumns.TYPE, value);
        return this;
    }


    /**
     * Values are stored in 100 cent.
     */
    public IntervalContentValues putValue(int value) {
        mContentValues.put(IntervalColumns.VALUE, value);
        return this;
    }

}
