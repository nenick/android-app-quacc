package de.nenick.quacc.database.provider.bookinginterval;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Booking which will be repeated over time period.
 */
public interface BookingIntervalModel extends BaseModel {

    /**
     * Referenced account.
     */
    long getAccountId();

    /**
     * Referenced category.
     */
    long getCategoryId();

    /**
     * Optional description for booking entries.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Strategy how to repeat.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Date when the interval creates the first booking entry.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateStart();

    /**
     * Date when the interval create no more booking entries. This must not match the last created booking date.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateEnd();

    /**
     * This is the last booking entry created by this interval.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateLast();

    /**
     * Necessary booking entries are created or updated until this date.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateUpdatedUntil();

    /**
     * direction how booking entries are created.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDirection();

    /**
     * Values are stored in cents.
     */
    int getAmount();
}
