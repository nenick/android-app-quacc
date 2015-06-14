package de.nenick.quacc.database.provider.interval;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code interval} table.
 */
public interface IntervalModel extends BaseModel {

    /**
     * Get the {@code account_id} value.
     */
    long getAccountId();

    /**
     * Get the {@code category_id} value.
     */
    long getCategoryId();

    /**
     * Get the {@code comment} value.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * The type of this interval how it will add accounting.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Start date when the interval have the first accounting.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateStart();

    /**
     * Last date where the interval is active. This must not match the last accounting date.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateEnd();

    /**
     * Until this date all accounting which belong to this interval are created or updated.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDateUpdatedUntil();

    /**
     * Get the {@code type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getType();

    /**
     * Values are stored in 100 cent.
     */
    int getValue();
}
