package de.nenick.quacc.database.provider.accountingcategory;

import android.database.Cursor;
import android.support.annotation.NonNull;

import de.nenick.quacc.database.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code accounting_category} table.
 */
public class AccountingCategoryCursor extends AbstractCursor implements AccountingCategoryModel {
    public AccountingCategoryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(AccountingCategoryColumns._ID);
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
        String res = getStringOrNull(AccountingCategoryColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
