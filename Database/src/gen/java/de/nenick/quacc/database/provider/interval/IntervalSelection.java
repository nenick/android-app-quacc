package de.nenick.quacc.database.provider.interval;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code interval} table.
 */
public class IntervalSelection extends AbstractSelection<IntervalSelection> {
    @Override
    protected Uri baseUri() {
        return IntervalColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code IntervalCursor} object, which is positioned before the first entry, or null.
     */
    public IntervalCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new IntervalCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public IntervalCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public IntervalCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public IntervalSelection id(long... value) {
        addEquals("interval." + IntervalColumns._ID, toObjectArray(value));
        return this;
    }

    public IntervalSelection accountId(long... value) {
        addEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalSelection accountIdNot(long... value) {
        addNotEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalSelection accountIdGt(long value) {
        addGreaterThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalSelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalSelection accountIdLt(long value) {
        addLessThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalSelection accountIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalSelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalSelection accountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalSelection accountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalSelection accountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalSelection accountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalSelection accountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalSelection accountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalSelection categoryId(long... value) {
        addEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalSelection categoryIdNot(long... value) {
        addNotEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalSelection categoryIdGt(long value) {
        addGreaterThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalSelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalSelection categoryIdLt(long value) {
        addLessThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalSelection categoryIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalSelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalSelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalSelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection categoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalSelection categoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalSelection categoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalSelection categoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalSelection categoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalSelection categoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalSelection categoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalSelection comment(String... value) {
        addEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection commentNot(String... value) {
        addNotEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection commentLike(String... value) {
        addLike(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection commentContains(String... value) {
        addContains(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection commentStartsWith(String... value) {
        addStartsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection commentEndsWith(String... value) {
        addEndsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalSelection interval(String... value) {
        addEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection intervalNot(String... value) {
        addNotEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection intervalLike(String... value) {
        addLike(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection intervalContains(String... value) {
        addContains(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection intervalStartsWith(String... value) {
        addStartsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection intervalEndsWith(String... value) {
        addEndsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalSelection dateStart(Date... value) {
        addEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateStartNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateStart(long... value) {
        addEquals(IntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public IntervalSelection dateStartAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateStartAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateStartBefore(Date value) {
        addLessThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateStartBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalSelection dateEnd(Date... value) {
        addEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateEndNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateEnd(long... value) {
        addEquals(IntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public IntervalSelection dateEndAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateEndAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateEndBefore(Date value) {
        addLessThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateEndBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalSelection dateLast(Date... value) {
        addEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateLastNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateLast(long... value) {
        addEquals(IntervalColumns.DATE_LAST, toObjectArray(value));
        return this;
    }

    public IntervalSelection dateLastAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateLastAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateLastBefore(Date value) {
        addLessThan(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateLastBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntil(Date... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntilNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntil(long... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public IntervalSelection dateUpdatedUntilAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntilBefore(Date value) {
        addLessThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection dateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalSelection type(String... value) {
        addEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection typeNot(String... value) {
        addNotEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection typeLike(String... value) {
        addLike(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection typeContains(String... value) {
        addContains(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection typeStartsWith(String... value) {
        addStartsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection typeEndsWith(String... value) {
        addEndsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalSelection value(int... value) {
        addEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalSelection valueNot(int... value) {
        addNotEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalSelection valueGt(int value) {
        addGreaterThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalSelection valueGtEq(int value) {
        addGreaterThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalSelection valueLt(int value) {
        addLessThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalSelection valueLtEq(int value) {
        addLessThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }
}
