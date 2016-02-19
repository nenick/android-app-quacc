package de.nenick.quacc.database.provider.bookinginterval;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_interval} table.
 */
public class BookingIntervalContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingIntervalColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingIntervalSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Referenced account.
     */
    public BookingIntervalContentValues putAccountId(long value) {
        mContentValues.put(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }


    /**
     * Referenced category.
     */
    public BookingIntervalContentValues putCategoryId(long value) {
        mContentValues.put(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }


    /**
     * Optional description for booking entries.
     */
    public BookingIntervalContentValues putComment(@Nullable String value) {
        mContentValues.put(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalContentValues putCommentNull() {
        mContentValues.putNull(BookingIntervalColumns.COMMENT);
        return this;
    }

    /**
     * Strategy how to repeat.
     */
    public BookingIntervalContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(BookingIntervalColumns.INTERVAL, value);
        return this;
    }


    /**
     * Date when the interval creates the first booking entry.
     */
    public BookingIntervalContentValues putDateStart(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateStart must not be null");
        mContentValues.put(BookingIntervalColumns.DATE_START, value.getTime());
        return this;
    }


    public BookingIntervalContentValues putDateStart(long value) {
        mContentValues.put(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    /**
     * Date when the interval create no more booking entries. This must not match the last created booking date.
     */
    public BookingIntervalContentValues putDateEnd(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateEnd must not be null");
        mContentValues.put(BookingIntervalColumns.DATE_END, value.getTime());
        return this;
    }


    public BookingIntervalContentValues putDateEnd(long value) {
        mContentValues.put(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    /**
     * This is the last booking entry created by this interval.
     */
    public BookingIntervalContentValues putDateLast(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateLast must not be null");
        mContentValues.put(BookingIntervalColumns.DATE_LAST, value.getTime());
        return this;
    }


    public BookingIntervalContentValues putDateLast(long value) {
        mContentValues.put(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    /**
     * Necessary booking entries are created or updated until this date.
     */
    public BookingIntervalContentValues putDateUpdatedUntil(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("dateUpdatedUntil must not be null");
        mContentValues.put(BookingIntervalColumns.DATE_UPDATED_UNTIL, value.getTime());
        return this;
    }


    public BookingIntervalContentValues putDateUpdatedUntil(long value) {
        mContentValues.put(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    /**
     * direction how booking entries are created.
     */
    public BookingIntervalContentValues putDirection(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("direction must not be null");
        mContentValues.put(BookingIntervalColumns.DIRECTION, value);
        return this;
    }


    /**
     * Values are stored in cents.
     */
    public BookingIntervalContentValues putAmount(int value) {
        mContentValues.put(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

}
