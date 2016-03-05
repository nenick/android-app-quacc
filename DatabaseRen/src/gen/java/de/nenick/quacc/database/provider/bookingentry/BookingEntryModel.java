package de.nenick.quacc.database.provider.bookingentry;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Booking entry for specific account.
 */
public interface BookingEntryModel extends BaseModel {

    /**
     * Referenced account.
     */
    long getAccountId();

    /**
     * Referenced category.
     */
    long getCategoryId();

    /**
     * Optional description for this entry.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Entry was created by this interval strategy.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Booking is done at this date. May be past or future.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDate();

    /**
     * Defines how this booking entry will effect the account money.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDirection();

    /**
     * Values are stored in cents.
     */
    int getAmount();
}
