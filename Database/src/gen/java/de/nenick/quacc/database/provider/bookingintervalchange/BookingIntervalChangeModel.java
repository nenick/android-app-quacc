package de.nenick.quacc.database.provider.bookingintervalchange;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * History booking interval change.
 */
public interface BookingIntervalChangeModel extends BaseModel {

    /**
     * Referenced booking interval.
     */
    long getBookingIntervalId();

    /**
     * Mark if the change applies for all following up (true) or once for a single (false) booking entries.
     */
    boolean getFollowUp();

    /**
     * Date for the single entry change or for all entries since this date.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDate();

    /**
     * Optional description for the single entry change or for all entries since this date.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Values are stored in positive cents.
     * Can be {@code null}.
     */
    @Nullable
    Integer getAmount();
}
