package de.nenick.quacc.database.provider.accountinginterval;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code accounting_interval} table.
 */
public class AccountingIntervalCursor extends AbstractCursor implements AccountingIntervalModel {
    public AccountingIntervalCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(AccountingIntervalColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(AccountingIntervalColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
