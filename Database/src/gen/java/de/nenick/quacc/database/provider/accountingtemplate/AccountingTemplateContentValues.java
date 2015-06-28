package de.nenick.quacc.database.provider.accountingtemplate;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code accounting_template} table.
 */
public class AccountingTemplateContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return AccountingTemplateColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable AccountingTemplateSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public AccountingTemplateContentValues putAccountId(long value) {
        mContentValues.put(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }


    public AccountingTemplateContentValues putCategoryId(long value) {
        mContentValues.put(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }


    public AccountingTemplateContentValues putComment(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("comment must not be null");
        mContentValues.put(AccountingTemplateColumns.COMMENT, value);
        return this;
    }


    public AccountingTemplateContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }


    public AccountingTemplateContentValues putType(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("type must not be null");
        mContentValues.put(AccountingTemplateColumns.TYPE, value);
        return this;
    }


    /**
     * Values are stored in 100 cent.
     */
    public AccountingTemplateContentValues putValue(int value) {
        mContentValues.put(AccountingTemplateColumns.VALUE, value);
        return this;
    }

}
