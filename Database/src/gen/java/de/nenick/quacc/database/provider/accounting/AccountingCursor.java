package de.nenick.quacc.database.provider.accounting;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.accountingcategory.*;

/**
 * Cursor wrapper for the {@code accounting} table.
 */
public class AccountingCursor extends AbstractCursor implements AccountingModel {
    public AccountingCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(AccountingColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code account_id} value.
     */
    public long getAccountId() {
        Long res = getLongOrNull(AccountingColumns.ACCOUNT_ID);
        if (res == null)
            throw new NullPointerException("The value of 'account_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAccountName() {
        String res = getStringOrNull(AccountColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Short description
     * Can be {@code null}.
     */
    @Nullable
    public String getAccountDescription() {
        String res = getStringOrNull(AccountColumns.DESCRIPTION);
        return res;
    }

    /**
     * Short description of the accounting.
     * Can be {@code null}.
     */
    @Nullable
    public String getComment() {
        String res = getStringOrNull(AccountingColumns.COMMENT);
        return res;
    }

    /**
     * Get the {@code accounting_interval} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public AccountingInterval getAccountingInterval() {
        Integer intValue = getIntegerOrNull(AccountingColumns.ACCOUNTING_INTERVAL);
        if (intValue == null)
            throw new NullPointerException("The value of 'accounting_interval' in the database was null, which is not allowed according to the model definition");
        return AccountingInterval.values()[intValue];
    }

    /**
     * Get the {@code accounting_category_id} value.
     */
    public long getAccountingCategoryId() {
        Long res = getLongOrNull(AccountingColumns.ACCOUNTING_CATEGORY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'accounting_category_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAccountingCategoryName() {
        String res = getStringOrNull(AccountingCategoryColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code accounting_date} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getAccountingDate() {
        Date res = getDateOrNull(AccountingColumns.ACCOUNTING_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'accounting_date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code accounting_type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public AccountingType getAccountingType() {
        Integer intValue = getIntegerOrNull(AccountingColumns.ACCOUNTING_TYPE);
        if (intValue == null)
            throw new NullPointerException("The value of 'accounting_type' in the database was null, which is not allowed according to the model definition");
        return AccountingType.values()[intValue];
    }

    /**
     * Values are stored with two decimals (1 Euro = 100)
     */
    public int getValue() {
        Integer res = getIntegerOrNull(AccountingColumns.VALUE);
        if (res == null)
            throw new NullPointerException("The value of 'value' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
