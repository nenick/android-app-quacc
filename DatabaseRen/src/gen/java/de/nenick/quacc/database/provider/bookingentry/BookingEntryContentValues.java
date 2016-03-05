package de.nenick.quacc.database.provider.bookingentry;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_entry} table.
 */
public class BookingEntryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingEntryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingEntrySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Referenced account.
     */
    public BookingEntryContentValues putAccountId(long value) {
        mContentValues.put(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }


    /**
     * Referenced category.
     */
    public BookingEntryContentValues putCategoryId(long value) {
        mContentValues.put(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }


    /**
     * Optional description for this entry.
     */
    public BookingEntryContentValues putComment(@Nullable String value) {
        mContentValues.put(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntryContentValues putCommentNull() {
        mContentValues.putNull(BookingEntryColumns.COMMENT);
        return this;
    }

    /**
     * Entry was created by this interval strategy.
     */
    public BookingEntryContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(BookingEntryColumns.INTERVAL, value);
        return this;
    }


    /**
     * Booking is done at this date. May be past or future.
     */
    public BookingEntryContentValues putDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("date must not be null");
        mContentValues.put(BookingEntryColumns.DATE, value.getTime());
        return this;
    }


    public BookingEntryContentValues putDate(long value) {
        mContentValues.put(BookingEntryColumns.DATE, value);
        return this;
    }

    /**
     * Defines how this booking entry will effect the account money.
     */
    public BookingEntryContentValues putDirection(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("direction must not be null");
        mContentValues.put(BookingEntryColumns.DIRECTION, value);
        return this;
    }


    /**
     * Values are stored in cents.
     */
    public BookingEntryContentValues putAmount(int value) {
        mContentValues.put(BookingEntryColumns.AMOUNT, value);
        return this;
    }

}
