package de.nenick.quacc.database.provider.accounting;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code accounting} table.
 */
public class AccountingSelection extends AbstractSelection<AccountingSelection> {
    @Override
    protected Uri baseUri() {
        return AccountingColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code AccountingCursor} object, which is positioned before the first entry, or null.
     */
    public AccountingCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AccountingCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public AccountingCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public AccountingCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AccountingSelection id(long... value) {
        addEquals("accounting." + AccountingColumns._ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountId(long... value) {
        addEquals(AccountingColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountIdNot(long... value) {
        addNotEquals(AccountingColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountIdGt(long value) {
        addGreaterThan(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingSelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingSelection accountIdLt(long value) {
        addLessThan(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingSelection accountIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingSelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountDescription(String... value) {
        addEquals(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountDescriptionNot(String... value) {
        addNotEquals(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountDescriptionLike(String... value) {
        addLike(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountDescriptionContains(String... value) {
        addContains(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountDescriptionStartsWith(String... value) {
        addStartsWith(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountDescriptionEndsWith(String... value) {
        addEndsWith(AccountColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection categoryId(long... value) {
        addEquals(AccountingColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection categoryIdNot(long... value) {
        addNotEquals(AccountingColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection categoryIdGt(long value) {
        addGreaterThan(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection categoryIdLt(long value) {
        addLessThan(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection categoryIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingSelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection categoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection categoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection categoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection categoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection categoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection categoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingSelection comment(String... value) {
        addEquals(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection commentNot(String... value) {
        addNotEquals(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection commentLike(String... value) {
        addLike(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection commentContains(String... value) {
        addContains(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection commentStartsWith(String... value) {
        addStartsWith(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection commentEndsWith(String... value) {
        addEndsWith(AccountingColumns.COMMENT, value);
        return this;
    }

    public AccountingSelection interval(String... value) {
        addEquals(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection intervalNot(String... value) {
        addNotEquals(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection intervalLike(String... value) {
        addLike(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection intervalContains(String... value) {
        addContains(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection intervalStartsWith(String... value) {
        addStartsWith(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection intervalEndsWith(String... value) {
        addEndsWith(AccountingColumns.INTERVAL, value);
        return this;
    }

    public AccountingSelection date(Date... value) {
        addEquals(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection dateNot(Date... value) {
        addNotEquals(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection date(long... value) {
        addEquals(AccountingColumns.DATE, toObjectArray(value));
        return this;
    }

    public AccountingSelection dateAfter(Date value) {
        addGreaterThan(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection dateAfterEq(Date value) {
        addGreaterThanOrEquals(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection dateBefore(Date value) {
        addLessThan(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection dateBeforeEq(Date value) {
        addLessThanOrEquals(AccountingColumns.DATE, value);
        return this;
    }

    public AccountingSelection type(String... value) {
        addEquals(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection typeNot(String... value) {
        addNotEquals(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection typeLike(String... value) {
        addLike(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection typeContains(String... value) {
        addContains(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection typeStartsWith(String... value) {
        addStartsWith(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection typeEndsWith(String... value) {
        addEndsWith(AccountingColumns.TYPE, value);
        return this;
    }

    public AccountingSelection value(int... value) {
        addEquals(AccountingColumns.VALUE, toObjectArray(value));
        return this;
    }

    public AccountingSelection valueNot(int... value) {
        addNotEquals(AccountingColumns.VALUE, toObjectArray(value));
        return this;
    }

    public AccountingSelection valueGt(int value) {
        addGreaterThan(AccountingColumns.VALUE, value);
        return this;
    }

    public AccountingSelection valueGtEq(int value) {
        addGreaterThanOrEquals(AccountingColumns.VALUE, value);
        return this;
    }

    public AccountingSelection valueLt(int value) {
        addLessThan(AccountingColumns.VALUE, value);
        return this;
    }

    public AccountingSelection valueLtEq(int value) {
        addLessThanOrEquals(AccountingColumns.VALUE, value);
        return this;
    }
}
