package de.nenick.quacc.database.provider.intervalchange;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code interval_change} table.
 */
public interface IntervalChangeModel extends BaseModel {

    /**
     * Get the {@code interval_id} value.
     */
    long getIntervalId();

    /**
     * Get the {@code change} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getChange();

    /**
     * Get the {@code date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getDate();

    /**
     * Get the {@code comment} value.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Values are stored in 100 cent.
     * Can be {@code null}.
     */
    @Nullable
    Integer getValue();
}
