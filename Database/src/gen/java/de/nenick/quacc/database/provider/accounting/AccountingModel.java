package de.nenick.quacc.database.provider.accounting;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.provider.base.BaseModel;

/**
 * Accounting
 */
public interface AccountingModel extends BaseModel {

    /**
     * Get the {@code account_id} value.
     */
    long getAccountId();

    /**
     * Short description of the accounting.
     * Can be {@code null}.
     */
    @Nullable
    String getComment();

    /**
     * Get the {@code accounting_interval} value.
     * Cannot be {@code null}.
     */
    @NonNull
    AccountingInterval getAccountingInterval();

    /**
     * Get the {@code accounting_category_id} value.
     */
    long getAccountingCategoryId();

    /**
     * Get the {@code accounting_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    Date getAccountingDate();

    /**
     * Get the {@code accounting_type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    AccountingType getAccountingType();

    /**
     * Values are stored with two decimals (1 Euro = 100)
     */
    int getValue();
}
