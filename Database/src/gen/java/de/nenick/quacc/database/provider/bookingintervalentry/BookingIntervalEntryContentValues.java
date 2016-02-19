package de.nenick.quacc.database.provider.bookingintervalentry;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_interval_entry} table.
 */
public class BookingIntervalEntryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingIntervalEntryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingIntervalEntrySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Referenced booking interval.
     */
    public BookingIntervalEntryContentValues putBookingIntervalId(long value) {
        mContentValues.put(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }


    /**
     * Referenced booking entry.
     */
    public BookingIntervalEntryContentValues putBookingEntryId(long value) {
        mContentValues.put(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, value);
        return this;
    }

}
