package de.nenick.quacc.database.provider.bookingintervalchange;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_interval_change} table.
 */
public class BookingIntervalChangeContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingIntervalChangeColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingIntervalChangeSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Referenced booking interval.
     */
    public BookingIntervalChangeContentValues putBookingIntervalId(long value) {
        mContentValues.put(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }


    /**
     * Mark if the change applies for all following up (true) or once for a single (false) booking entries.
     */
    public BookingIntervalChangeContentValues putFollowUp(boolean value) {
        mContentValues.put(BookingIntervalChangeColumns.FOLLOW_UP, value);
        return this;
    }


    /**
     * Date for the single entry change or for all entries since this date.
     */
    public BookingIntervalChangeContentValues putDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("date must not be null");
        mContentValues.put(BookingIntervalChangeColumns.DATE, value.getTime());
        return this;
    }


    public BookingIntervalChangeContentValues putDate(long value) {
        mContentValues.put(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    /**
     * Optional description for the single entry change or for all entries since this date.
     */
    public BookingIntervalChangeContentValues putComment(@Nullable String value) {
        mContentValues.put(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeContentValues putCommentNull() {
        mContentValues.putNull(BookingIntervalChangeColumns.COMMENT);
        return this;
    }

    /**
     * Values are stored in positive cents.
     */
    public BookingIntervalChangeContentValues putAmount(@Nullable Integer value) {
        mContentValues.put(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeContentValues putAmountNull() {
        mContentValues.putNull(BookingIntervalChangeColumns.AMOUNT);
        return this;
    }
}
