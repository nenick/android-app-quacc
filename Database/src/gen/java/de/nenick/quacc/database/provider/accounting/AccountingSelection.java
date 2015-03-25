package de.nenick.quacc.database.provider.accounting;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.accountinginterval.*;
import de.nenick.quacc.database.provider.accountingcategory.*;

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

    public AccountingSelection name(String... value) {
        addEquals(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection nameNot(String... value) {
        addNotEquals(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection nameLike(String... value) {
        addLike(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection nameContains(String... value) {
        addContains(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection nameStartsWith(String... value) {
        addStartsWith(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection nameEndsWith(String... value) {
        addEndsWith(AccountingColumns.NAME, value);
        return this;
    }

    public AccountingSelection description(String... value) {
        addEquals(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection descriptionNot(String... value) {
        addNotEquals(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection descriptionLike(String... value) {
        addLike(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection descriptionContains(String... value) {
        addContains(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection descriptionStartsWith(String... value) {
        addStartsWith(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection descriptionEndsWith(String... value) {
        addEndsWith(AccountingColumns.DESCRIPTION, value);
        return this;
    }

    public AccountingSelection accountingIntervalId(long... value) {
        addEquals(AccountingColumns.ACCOUNTING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountingIntervalIdNot(long... value) {
        addNotEquals(AccountingColumns.ACCOUNTING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountingIntervalIdGt(long value) {
        addGreaterThan(AccountingColumns.ACCOUNTING_INTERVAL_ID, value);
        return this;
    }

    public AccountingSelection accountingIntervalIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.ACCOUNTING_INTERVAL_ID, value);
        return this;
    }

    public AccountingSelection accountingIntervalIdLt(long value) {
        addLessThan(AccountingColumns.ACCOUNTING_INTERVAL_ID, value);
        return this;
    }

    public AccountingSelection accountingIntervalIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.ACCOUNTING_INTERVAL_ID, value);
        return this;
    }

    public AccountingSelection accountingIntervalName(String... value) {
        addEquals(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingIntervalNameNot(String... value) {
        addNotEquals(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingIntervalNameLike(String... value) {
        addLike(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingIntervalNameContains(String... value) {
        addContains(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingIntervalNameStartsWith(String... value) {
        addStartsWith(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingIntervalNameEndsWith(String... value) {
        addEndsWith(AccountingIntervalColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryId(long... value) {
        addEquals(AccountingColumns.ACCOUNTING_CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountingCategoryIdNot(long... value) {
        addNotEquals(AccountingColumns.ACCOUNTING_CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountingCategoryIdGt(long value) {
        addGreaterThan(AccountingColumns.ACCOUNTING_CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection accountingCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.ACCOUNTING_CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection accountingCategoryIdLt(long value) {
        addLessThan(AccountingColumns.ACCOUNTING_CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection accountingCategoryIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.ACCOUNTING_CATEGORY_ID, value);
        return this;
    }

    public AccountingSelection accountingCategoryName(String... value) {
        addEquals(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryNameNot(String... value) {
        addNotEquals(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryNameLike(String... value) {
        addLike(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryNameContains(String... value) {
        addContains(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryNameStartsWith(String... value) {
        addStartsWith(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingCategoryNameEndsWith(String... value) {
        addEndsWith(AccountingCategoryColumns.NAME, value);
        return this;
    }

    public AccountingSelection accountingDate(Date... value) {
        addEquals(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingDateNot(Date... value) {
        addNotEquals(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingDate(long... value) {
        addEquals(AccountingColumns.ACCOUNTING_DATE, toObjectArray(value));
        return this;
    }

    public AccountingSelection accountingDateAfter(Date value) {
        addGreaterThan(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingDateAfterEq(Date value) {
        addGreaterThanOrEquals(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingDateBefore(Date value) {
        addLessThan(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingDateBeforeEq(Date value) {
        addLessThanOrEquals(AccountingColumns.ACCOUNTING_DATE, value);
        return this;
    }

    public AccountingSelection accountingType(AccountingType... value) {
        addEquals(AccountingColumns.ACCOUNTING_TYPE, value);
        return this;
    }

    public AccountingSelection accountingTypeNot(AccountingType... value) {
        addNotEquals(AccountingColumns.ACCOUNTING_TYPE, value);
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
