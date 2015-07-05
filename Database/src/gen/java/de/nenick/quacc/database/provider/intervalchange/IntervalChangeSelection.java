package de.nenick.quacc.database.provider.intervalchange;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.interval.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code interval_change} table.
 */
public class IntervalChangeSelection extends AbstractSelection<IntervalChangeSelection> {
    @Override
    protected Uri baseUri() {
        return IntervalChangeColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code IntervalChangeCursor} object, which is positioned before the first entry, or null.
     */
    public IntervalChangeCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new IntervalChangeCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public IntervalChangeCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public IntervalChangeCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public IntervalChangeSelection id(long... value) {
        addEquals("interval_change." + IntervalChangeColumns._ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalId(long... value) {
        addEquals(IntervalChangeColumns.INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalIdNot(long... value) {
        addNotEquals(IntervalChangeColumns.INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalIdGt(long value) {
        addGreaterThan(IntervalChangeColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalChangeColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalIdLt(long value) {
        addLessThan(IntervalChangeColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalIdLtEq(long value) {
        addLessThanOrEquals(IntervalChangeColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountId(long... value) {
        addEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalAccountIdNot(long... value) {
        addNotEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalAccountIdGt(long value) {
        addGreaterThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountIdLt(long value) {
        addLessThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryId(long... value) {
        addEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalCategoryIdNot(long... value) {
        addNotEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalCategoryIdGt(long value) {
        addGreaterThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIdLt(long value) {
        addLessThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalChangeSelection intervalCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalChangeSelection intervalComment(String... value) {
        addEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalCommentNot(String... value) {
        addNotEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalCommentLike(String... value) {
        addLike(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalCommentContains(String... value) {
        addContains(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalCommentStartsWith(String... value) {
        addStartsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalCommentEndsWith(String... value) {
        addEndsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection intervalInterval(String... value) {
        addEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalIntervalNot(String... value) {
        addNotEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalIntervalLike(String... value) {
        addLike(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalIntervalContains(String... value) {
        addContains(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalIntervalStartsWith(String... value) {
        addStartsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalIntervalEndsWith(String... value) {
        addEndsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStart(Date... value) {
        addEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStartNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStart(long... value) {
        addEquals(IntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalDateStartAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStartAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStartBefore(Date value) {
        addLessThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateStartBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEnd(Date... value) {
        addEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEndNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEnd(long... value) {
        addEquals(IntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalDateEndAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEndAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEndBefore(Date value) {
        addLessThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateEndBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLast(Date... value) {
        addEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLastNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLast(long... value) {
        addEquals(IntervalColumns.DATE_LAST, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalDateLastAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLastAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLastBefore(Date value) {
        addLessThan(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateLastBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_LAST, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntil(Date... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntilNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntil(long... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntilAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntilBefore(Date value) {
        addLessThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalDateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalChangeSelection intervalType(String... value) {
        addEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalTypeNot(String... value) {
        addNotEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalTypeLike(String... value) {
        addLike(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalTypeContains(String... value) {
        addContains(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalTypeStartsWith(String... value) {
        addStartsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalTypeEndsWith(String... value) {
        addEndsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalChangeSelection intervalValue(int... value) {
        addEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalValueNot(int... value) {
        addNotEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection intervalValueGt(int value) {
        addGreaterThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalValueGtEq(int value) {
        addGreaterThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalValueLt(int value) {
        addLessThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection intervalValueLtEq(int value) {
        addLessThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection change(String... value) {
        addEquals(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection changeNot(String... value) {
        addNotEquals(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection changeLike(String... value) {
        addLike(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection changeContains(String... value) {
        addContains(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection changeStartsWith(String... value) {
        addStartsWith(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection changeEndsWith(String... value) {
        addEndsWith(IntervalChangeColumns.CHANGE, value);
        return this;
    }

    public IntervalChangeSelection date(Date... value) {
        addEquals(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection dateNot(Date... value) {
        addNotEquals(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection date(long... value) {
        addEquals(IntervalChangeColumns.DATE, toObjectArray(value));
        return this;
    }

    public IntervalChangeSelection dateAfter(Date value) {
        addGreaterThan(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection dateAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection dateBefore(Date value) {
        addLessThan(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection dateBeforeEq(Date value) {
        addLessThanOrEquals(IntervalChangeColumns.DATE, value);
        return this;
    }

    public IntervalChangeSelection comment(String... value) {
        addEquals(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection commentNot(String... value) {
        addNotEquals(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection commentLike(String... value) {
        addLike(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection commentContains(String... value) {
        addContains(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection commentStartsWith(String... value) {
        addStartsWith(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection commentEndsWith(String... value) {
        addEndsWith(IntervalChangeColumns.COMMENT, value);
        return this;
    }

    public IntervalChangeSelection value(Integer... value) {
        addEquals(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection valueNot(Integer... value) {
        addNotEquals(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection valueGt(int value) {
        addGreaterThan(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection valueGtEq(int value) {
        addGreaterThanOrEquals(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection valueLt(int value) {
        addLessThan(IntervalChangeColumns.VALUE, value);
        return this;
    }

    public IntervalChangeSelection valueLtEq(int value) {
        addLessThanOrEquals(IntervalChangeColumns.VALUE, value);
        return this;
    }
}
