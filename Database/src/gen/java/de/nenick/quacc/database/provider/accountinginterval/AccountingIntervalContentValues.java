package de.nenick.quacc.database.provider.accountinginterval;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code accounting_interval} table.
 */
public class AccountingIntervalContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return AccountingIntervalColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable AccountingIntervalSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Name
     */
    public AccountingIntervalContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(AccountingIntervalColumns.NAME, value);
        return this;
    }

}
