package de.nenick.quacc.database.provider.intervalaccounting;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.interval.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;
import de.nenick.quacc.database.provider.accounting.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code interval_accounting} table.
 */
public class IntervalAccountingSelection extends AbstractSelection<IntervalAccountingSelection> {
    @Override
    protected Uri baseUri() {
        return IntervalAccountingColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code IntervalAccountingCursor} object, which is positioned before the first entry, or null.
     */
    public IntervalAccountingCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new IntervalAccountingCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public IntervalAccountingCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public IntervalAccountingCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public IntervalAccountingSelection id(long... value) {
        addEquals("interval_accounting." + IntervalAccountingColumns._ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalId(long... value) {
        addEquals(IntervalAccountingColumns.INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalIdNot(long... value) {
        addNotEquals(IntervalAccountingColumns.INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalIdGt(long value) {
        addGreaterThan(IntervalAccountingColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalAccountingColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalIdLt(long value) {
        addLessThan(IntervalAccountingColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalIdLtEq(long value) {
        addLessThanOrEquals(IntervalAccountingColumns.INTERVAL_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountId(long... value) {
        addEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalAccountIdNot(long... value) {
        addNotEquals(IntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalAccountIdGt(long value) {
        addGreaterThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountIdLt(long value) {
        addLessThan(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryId(long... value) {
        addEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIdNot(long... value) {
        addNotEquals(IntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIdGt(long value) {
        addGreaterThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIdLt(long value) {
        addLessThan(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIdLtEq(long value) {
        addLessThanOrEquals(IntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection intervalCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection intervalComment(String... value) {
        addEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalCommentNot(String... value) {
        addNotEquals(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalCommentLike(String... value) {
        addLike(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalCommentContains(String... value) {
        addContains(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalCommentStartsWith(String... value) {
        addStartsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalCommentEndsWith(String... value) {
        addEndsWith(IntervalColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection intervalInterval(String... value) {
        addEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalIntervalNot(String... value) {
        addNotEquals(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalIntervalLike(String... value) {
        addLike(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalIntervalContains(String... value) {
        addContains(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalIntervalStartsWith(String... value) {
        addStartsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalIntervalEndsWith(String... value) {
        addEndsWith(IntervalColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStart(Date... value) {
        addEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStartNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStart(long... value) {
        addEquals(IntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalDateStartAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStartAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStartBefore(Date value) {
        addLessThan(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateStartBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_START, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEnd(Date... value) {
        addEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEndNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEnd(long... value) {
        addEquals(IntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalDateEndAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEndAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEndBefore(Date value) {
        addLessThan(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateEndBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_END, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntil(Date... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntilNot(Date... value) {
        addNotEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntil(long... value) {
        addEquals(IntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntilAfter(Date value) {
        addGreaterThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntilBefore(Date value) {
        addLessThan(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalDateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(IntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public IntervalAccountingSelection intervalType(String... value) {
        addEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalTypeNot(String... value) {
        addNotEquals(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalTypeLike(String... value) {
        addLike(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalTypeContains(String... value) {
        addContains(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalTypeStartsWith(String... value) {
        addStartsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalTypeEndsWith(String... value) {
        addEndsWith(IntervalColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection intervalValue(int... value) {
        addEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalValueNot(int... value) {
        addNotEquals(IntervalColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection intervalValueGt(int value) {
        addGreaterThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalValueGtEq(int value) {
        addGreaterThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalValueLt(int value) {
        addLessThan(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection intervalValueLtEq(int value) {
        addLessThanOrEquals(IntervalColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingId(long... value) {
        addEquals(IntervalAccountingColumns.ACCOUNTING_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingIdNot(long... value) {
        addNotEquals(IntervalAccountingColumns.ACCOUNTING_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingIdGt(long value) {
        addGreaterThan(IntervalAccountingColumns.ACCOUNTING_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingIdGtEq(long value) {
        addGreaterThanOrEquals(IntervalAccountingColumns.ACCOUNTING_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingIdLt(long value) {
        addLessThan(IntervalAccountingColumns.ACCOUNTING_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingIdLtEq(long value) {
        addLessThanOrEquals(IntervalAccountingColumns.ACCOUNTING_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountId(long... value) {
        addEquals(AccountingColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingAccountIdNot(long... value) {
        addNotEquals(AccountingColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingAccountIdGt(long value) {
        addGreaterThan(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountIdLt(long value) {
        addLessThan(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.ACCOUNT_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryId(long... value) {
        addEquals(AccountingColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIdNot(long... value) {
        addNotEquals(AccountingColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIdGt(long value) {
        addGreaterThan(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIdLt(long value) {
        addLessThan(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIdLtEq(long value) {
        addLessThanOrEquals(AccountingColumns.CATEGORY_ID, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection accountingCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public IntervalAccountingSelection accountingComment(String... value) {
        addEquals(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingCommentNot(String... value) {
        addNotEquals(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingCommentLike(String... value) {
        addLike(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingCommentContains(String... value) {
        addContains(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingCommentStartsWith(String... value) {
        addStartsWith(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingCommentEndsWith(String... value) {
        addEndsWith(AccountingColumns.COMMENT, value);
        return this;
    }

    public IntervalAccountingSelection accountingInterval(String... value) {
        addEquals(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingIntervalNot(String... value) {
        addNotEquals(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingIntervalLike(String... value) {
        addLike(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingIntervalContains(String... value) {
        addContains(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingIntervalStartsWith(String... value) {
        addStartsWith(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingIntervalEndsWith(String... value) {
        addEndsWith(AccountingColumns.INTERVAL, value);
        return this;
    }

    public IntervalAccountingSelection accountingDate(Date... value) {
        addEquals(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingDateNot(Date... value) {
        addNotEquals(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingDate(long... value) {
        addEquals(AccountingColumns.DATE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingDateAfter(Date value) {
        addGreaterThan(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingDateAfterEq(Date value) {
        addGreaterThanOrEquals(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingDateBefore(Date value) {
        addLessThan(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingDateBeforeEq(Date value) {
        addLessThanOrEquals(AccountingColumns.DATE, value);
        return this;
    }

    public IntervalAccountingSelection accountingType(String... value) {
        addEquals(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingTypeNot(String... value) {
        addNotEquals(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingTypeLike(String... value) {
        addLike(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingTypeContains(String... value) {
        addContains(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingTypeStartsWith(String... value) {
        addStartsWith(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingTypeEndsWith(String... value) {
        addEndsWith(AccountingColumns.TYPE, value);
        return this;
    }

    public IntervalAccountingSelection accountingValue(int... value) {
        addEquals(AccountingColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingValueNot(int... value) {
        addNotEquals(AccountingColumns.VALUE, toObjectArray(value));
        return this;
    }

    public IntervalAccountingSelection accountingValueGt(int value) {
        addGreaterThan(AccountingColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingValueGtEq(int value) {
        addGreaterThanOrEquals(AccountingColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingValueLt(int value) {
        addLessThan(AccountingColumns.VALUE, value);
        return this;
    }

    public IntervalAccountingSelection accountingValueLtEq(int value) {
        addLessThanOrEquals(AccountingColumns.VALUE, value);
        return this;
    }
}
