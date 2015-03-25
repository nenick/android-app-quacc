package de.nenick.quacc.database.provider.accountingcategory;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;

/**
 * Selection for the {@code accounting_category} table.
 */
public class AccountingCategorySelection extends AbstractSelection<AccountingCategorySelection> {
    @Override
    protected Uri baseUri() {
        return AccountingCategoryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code AccountingCategoryCursor} object, which is positioned before the first entry, or null.
     */
    public AccountingCategoryCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AccountingCategoryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public AccountingCategoryCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public AccountingCategoryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AccountingCategorySelection id(long... value) {
        addEquals("accounting_category." + AccountingCategoryColumns._ID, toObjectArray(value));
        return this;
    }

    public AccountingCategorySelection name(String... value) {
        addEquals(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingCategorySelection nameNot(String... value) {
        addNotEquals(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingCategorySelection nameLike(String... value) {
        addLike(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingCategorySelection nameContains(String... value) {
        addContains(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingCategorySelection nameStartsWith(String... value) {
        addStartsWith(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingCategorySelection nameEndsWith(String... value) {
        addEndsWith(AccountingCategoryColumns.NAME, value);
        return this;
    }
}
