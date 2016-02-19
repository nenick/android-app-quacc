package de.nenick.quacc.database.provider.bookingintervalentry;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Reference to concrete booking entry for an interval.
 */
public interface BookingIntervalEntryModel extends BaseModel {

    /**
     * Referenced booking interval.
     */
    long getBookingIntervalId();

    /**
     * Referenced booking entry.
     */
    long getBookingEntryId();
}
