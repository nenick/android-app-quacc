package de.nenick.quacc.database.provider.bookingintervalentry;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.bookinginterval.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;
import de.nenick.quacc.database.provider.bookingentry.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_interval_entry} table.
 */
public class BookingIntervalEntrySelection extends AbstractSelection<BookingIntervalEntrySelection> {
    @Override
    protected Uri baseUri() {
        return BookingIntervalEntryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingIntervalEntryCursor} object, which is positioned before the first entry, or null.
     */
    public BookingIntervalEntryCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingIntervalEntryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingIntervalEntryCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingIntervalEntryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingIntervalEntrySelection id(long... value) {
        addEquals("booking_interval_entry." + BookingIntervalEntryColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalId(long... value) {
        addEquals(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIdNot(long... value) {
        addNotEquals(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIdGt(long value) {
        addGreaterThan(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIdLt(long value) {
        addLessThan(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountId(long... value) {
        addEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountIdLt(long value) {
        addLessThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryId(long... value) {
        addEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIdLt(long value) {
        addLessThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalComment(String... value) {
        addEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCommentNot(String... value) {
        addNotEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCommentLike(String... value) {
        addLike(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCommentContains(String... value) {
        addContains(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCommentStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalCommentEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalInterval(String... value) {
        addEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIntervalNot(String... value) {
        addNotEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIntervalLike(String... value) {
        addLike(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIntervalContains(String... value) {
        addContains(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIntervalStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalIntervalEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStart(Date... value) {
        addEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStartNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStart(long... value) {
        addEquals(BookingIntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStartAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStartAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStartBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateStartBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEnd(Date... value) {
        addEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEndNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEnd(long... value) {
        addEquals(BookingIntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEndAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEndAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEndBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateEndBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLast(Date... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLastNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLast(long... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLastAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLastAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLastBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateLastBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntil(Date... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntilNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntil(long... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntilAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntilBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirection(String... value) {
        addEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirectionNot(String... value) {
        addNotEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirectionLike(String... value) {
        addLike(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirectionContains(String... value) {
        addContains(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirectionStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalDirectionEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmount(int... value) {
        addEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmountNot(int... value) {
        addNotEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmountGt(int value) {
        addGreaterThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmountGtEq(int value) {
        addGreaterThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmountLt(int value) {
        addLessThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingIntervalAmountLtEq(int value) {
        addLessThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryId(long... value) {
        addEquals(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIdNot(long... value) {
        addNotEquals(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIdGt(long value) {
        addGreaterThan(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIdLt(long value) {
        addLessThan(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalEntryColumns.BOOKING_ENTRY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountId(long... value) {
        addEquals(BookingEntryColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountIdNot(long... value) {
        addNotEquals(BookingEntryColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountIdGt(long value) {
        addGreaterThan(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountIdLt(long value) {
        addLessThan(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountIdLtEq(long value) {
        addLessThanOrEquals(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryId(long... value) {
        addEquals(BookingEntryColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIdNot(long... value) {
        addNotEquals(BookingEntryColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIdGt(long value) {
        addGreaterThan(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIdLt(long value) {
        addLessThan(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIdLtEq(long value) {
        addLessThanOrEquals(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryComment(String... value) {
        addEquals(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCommentNot(String... value) {
        addNotEquals(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCommentLike(String... value) {
        addLike(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCommentContains(String... value) {
        addContains(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCommentStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryCommentEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryInterval(String... value) {
        addEquals(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIntervalNot(String... value) {
        addNotEquals(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIntervalLike(String... value) {
        addLike(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIntervalContains(String... value) {
        addContains(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIntervalStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryIntervalEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDate(Date... value) {
        addEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDateNot(Date... value) {
        addNotEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDate(long... value) {
        addEquals(BookingEntryColumns.DATE, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDateAfter(Date value) {
        addGreaterThan(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDateAfterEq(Date value) {
        addGreaterThanOrEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDateBefore(Date value) {
        addLessThan(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDateBeforeEq(Date value) {
        addLessThanOrEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirection(String... value) {
        addEquals(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirectionNot(String... value) {
        addNotEquals(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirectionLike(String... value) {
        addLike(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirectionContains(String... value) {
        addContains(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirectionStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryDirectionEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmount(int... value) {
        addEquals(BookingEntryColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmountNot(int... value) {
        addNotEquals(BookingEntryColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmountGt(int value) {
        addGreaterThan(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmountGtEq(int value) {
        addGreaterThanOrEquals(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmountLt(int value) {
        addLessThan(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalEntrySelection bookingEntryAmountLtEq(int value) {
        addLessThanOrEquals(BookingEntryColumns.AMOUNT, value);
        return this;
    }
}
