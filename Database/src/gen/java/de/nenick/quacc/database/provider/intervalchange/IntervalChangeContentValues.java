package de.nenick.quacc.database.provider.intervalchange;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code interval_change} table.
 */
public class IntervalChangeContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return IntervalChangeColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable IntervalChangeSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public IntervalChangeContentValues putIntervalId(long value) {
        mContentValues.put(IntervalChangeColumns.INTERVAL_ID, value);
        return this;
    }


    public IntervalChangeContentValues putChange(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("change must not be null");
        mContentValues.put(IntervalChangeColumns.CHANGE, value);
        return this;
    }


    public IntervalChangeContentValues putDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("date must not be null");
        mContentValues.put(IntervalChangeColumns.DATE, value.getTime());
        return this;
    }


    public IntervalChangeContentValues putDate(long value) {
        mContentValues.put(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeContentValues putComment(@Nullable String value) {
        mContentValues.put(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeContentValues putCommentNull() {
        mContentValues.putNull(IntervalChangeColumns.COMMENT);
        return this;
    }

    /**
     * Values are stored in 100 cent.
     */
    public IntervalChangeContentValues putValue(@Nullable Integer value) {
        mContentValues.put(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeContentValues putValueNull() {
        mContentValues.putNull(IntervalChangeColumns.VALUE);
        return this;
    }
}
