package de.nenick.quacc.database.provider.account;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;

/**
 * Selection for the {@code account} table.
 */
public class AccountSelection extends AbstractSelection<AccountSelection> {
    @Override
    protected Uri baseUri() {
        return AccountColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection      A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder       How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *                        order, which may be unordered.
     * @return A {@code AccountCursor} object, which is positioned before the first entry, or null.
     */
    public AccountCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AccountCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public AccountCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public AccountCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AccountSelection id(long... value) {
        addEquals("account." + AccountColumns._ID, toObjectArray(value));
        return this;
    }

    public AccountSelection name(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection nameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection nameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection nameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection nameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection nameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountSelection description(String... value) {
        addEquals(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountSelection descriptionNot(String... value) {
        addNotEquals(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountSelection descriptionLike(String... value) {
        addLike(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountSelection descriptionContains(String... value) {
        addContains(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountSelection descriptionStartsWith(String... value) {
        addStartsWith(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountSelection descriptionEndsWith(String... value) {
        addEndsWith(AccountColumns.DESCRIPTION, value);
        return this;
    }
}
