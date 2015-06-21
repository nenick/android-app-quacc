package de.nenick.quacc.database.provider.accountingtemplate;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code accounting_template} table.
 */
public class AccountingTemplateSelection extends AbstractSelection<AccountingTemplateSelection> {
    @Override
    protected Uri baseUri() {
        return AccountingTemplateColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code AccountingTemplateCursor} object, which is positioned before the first entry, or null.
     */
    public AccountingTemplateCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new AccountingTemplateCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public AccountingTemplateCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public AccountingTemplateCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public AccountingTemplateSelection id(long... value) {
        addEquals("accounting_template." + AccountingTemplateColumns._ID, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection accountId(long... value) {
        addEquals(AccountingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection accountIdNot(long... value) {
        addNotEquals(AccountingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection accountIdGt(long value) {
        addGreaterThan(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingTemplateSelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingTemplateSelection accountIdLt(long value) {
        addLessThan(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingTemplateSelection accountIdLtEq(long value) {
        addLessThanOrEquals(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public AccountingTemplateSelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection accountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection accountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection accountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public AccountingTemplateSelection accountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public AccountingTemplateSelection accountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public AccountingTemplateSelection accountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public AccountingTemplateSelection categoryId(long... value) {
        addEquals(AccountingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection categoryIdNot(long... value) {
        addNotEquals(AccountingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection categoryIdGt(long value) {
        addGreaterThan(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingTemplateSelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingTemplateSelection categoryIdLt(long value) {
        addLessThan(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingTemplateSelection categoryIdLtEq(long value) {
        addLessThanOrEquals(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public AccountingTemplateSelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public AccountingTemplateSelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public AccountingTemplateSelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection categoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection categoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection categoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public AccountingTemplateSelection categoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public AccountingTemplateSelection categoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public AccountingTemplateSelection categoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public AccountingTemplateSelection categoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public AccountingTemplateSelection comment(String... value) {
        addEquals(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection commentNot(String... value) {
        addNotEquals(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection commentLike(String... value) {
        addLike(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection commentContains(String... value) {
        addContains(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection commentStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection commentEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public AccountingTemplateSelection interval(String... value) {
        addEquals(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection intervalNot(String... value) {
        addNotEquals(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection intervalLike(String... value) {
        addLike(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection intervalContains(String... value) {
        addContains(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection intervalStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection intervalEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public AccountingTemplateSelection type(String... value) {
        addEquals(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection typeNot(String... value) {
        addNotEquals(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection typeLike(String... value) {
        addLike(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection typeContains(String... value) {
        addContains(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection typeStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection typeEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public AccountingTemplateSelection value(Integer... value) {
        addEquals(AccountingTemplateColumns.VALUE, value);
        return this;
    }

    public AccountingTemplateSelection valueNot(Integer... value) {
        addNotEquals(AccountingTemplateColumns.VALUE, value);
        return this;
    }

    public AccountingTemplateSelection valueGt(int value) {
        addGreaterThan(AccountingTemplateColumns.VALUE, value);
        return this;
    }

    public AccountingTemplateSelection valueGtEq(int value) {
        addGreaterThanOrEquals(AccountingTemplateColumns.VALUE, value);
        return this;
    }

    public AccountingTemplateSelection valueLt(int value) {
        addLessThan(AccountingTemplateColumns.VALUE, value);
        return this;
    }

    public AccountingTemplateSelection valueLtEq(int value) {
        addLessThanOrEquals(AccountingTemplateColumns.VALUE, value);
        return this;
    }
}
