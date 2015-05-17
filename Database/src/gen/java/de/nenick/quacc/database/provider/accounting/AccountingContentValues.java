package de.nenick.quacc.database.provider.accounting;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable AccountingSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public AccountingContentValues putAccountId(long value) {
        mContentValues.put(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }


    public AccountingContentValues putCategoryId(long value) {
        mContentValues.put(AccountingColumns.CATEGORY_ID, value);
        return this;
    }


    public AccountingContentValues putComment(@Nullable String value) {
        mContentValues.put(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingContentValues putCommentNull() {
        mContentValues.putNull(AccountingColumns.COMMENT);
        return this;
    }

    public AccountingContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(AccountingColumns.INTERVAL, value);
        return this;
    }


    public AccountingContentValues putDate(@NonNull Date value) {
        if (value == null) throw new IllegalArgumentException("date must not be null");
        mContentValues.put(AccountingColumns.DATE, value.getTime());
        return this;
    }


    public AccountingContentValues putDate(long value) {
        mContentValues.put(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingContentValues putType(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("type must not be null");
        mContentValues.put(AccountingColumns.TYPE, value);
        return this;
    }


    public AccountingContentValues putValue(int value) {
        mContentValues.put(AccountingColumns.VALUE, value);
        return this;
    }

}
