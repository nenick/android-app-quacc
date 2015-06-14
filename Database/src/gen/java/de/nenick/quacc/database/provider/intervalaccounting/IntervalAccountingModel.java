package de.nenick.quacc.database.provider.intervalaccounting;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code interval_accounting} table.
 */
public interface IntervalAccountingModel extends BaseModel {

    /**
     * Get the {@code interval_id} value.
     */
    long getIntervalId();

    /**
     * Get the {@code accounting_id} value.
     */
    long getAccountingId();
}
