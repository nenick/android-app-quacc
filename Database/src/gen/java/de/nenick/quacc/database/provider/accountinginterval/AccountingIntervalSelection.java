package de.nenick.quacc.database.provider.accountinginterval;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;

/**
 * Selection for the {@code accounting_interval} table.
 */
public class AccountingIntervalSelection extends AbstractSelection<AccountingIntervalSelection> {
    @Override
    protected Uri baseUri() {
        return AccountingIntervalColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code AccountingIntervalCursor} object, which is positioned before the first entry, or null.
     */
    public AccountingIntervalCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AccountingIntervalCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public AccountingIntervalCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public AccountingIntervalCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AccountingIntervalSelection id(long... value) {
        addEquals("accounting_interval." + AccountingIntervalColumns._ID, toObjectArray(value));
        return this;
    }

    public AccountingIntervalSelection name(String... value) {
        addEquals(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingIntervalSelection nameNot(String... value) {
        addNotEquals(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingIntervalSelection nameLike(String... value) {
        addLike(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingIntervalSelection nameContains(String... value) {
        addContains(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingIntervalSelection nameStartsWith(String... value) {
        addStartsWith(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingIntervalSelection nameEndsWith(String... value) {
        addEndsWith(AccountingIntervalColumns.NAME, value);
        return this;
    }
}
