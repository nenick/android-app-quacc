package de.nenick.quacc.database.provider.accounting;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code accounting} table.
 */
public class AccountingContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return AccountingColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where           The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable AccountingSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public AccountingContentValues putAccountId(long value) {
        mContentValues.put(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }


    /**
     * Short description of the accounting.
     */
    public AccountingContentValues putComment(@Nullable String value) {
        mContentValues.put(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingContentValues putCommentNull() {
        mContentValues.putNull(AccountingColumns.COMMENT);
        return this;
    }

    public AccountingContentValues putAccountingInterval(@NonNull AccountingInterval value) {
        if (value == null)
            throw new IllegalArgumentException("accountingInterval must not be null");
        mContentValues.put(AccountingColumns.ACCOUNTING_INTERVAL, value.ordinal());
        return this;
    }


    public AccountingContentValues putAccountingCategoryId(long value) {
        mContentValues.put(AccountingColumns.ACCOUNTING_CATEGORY_ID, value);
        return this;
    }


    public AccountingContentValues putAccountingDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("accountingDate must not be null");
        mContentValues.put(AccountingColumns.ACCOUNTING_DATE, value.getTime());
        return this;
    }


    public AccountingContentValues putAccountingDate(long value) {
        mContentValues.put(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingContentValues putAccountingType(@NonNull AccountingType value) {
        if (value == null) throw new IllegalArgumentException("accountingType must not be null");
        mContentValues.put(AccountingColumns.ACCOUNTING_TYPE, value.ordinal());
        return this;
    }


    /**
     * Values are stored with two decimals (1 Euro = 100)
     */
    public AccountingContentValues putValue(int value) {
        mContentValues.put(AccountingColumns.VALUE, value);
        return this;
    }

}
