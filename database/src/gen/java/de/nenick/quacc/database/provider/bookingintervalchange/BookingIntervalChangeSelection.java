package de.nenick.quacc.database.provider.bookingintervalchange;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.bookinginterval.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_interval_change} table.
 */
public class BookingIntervalChangeSelection extends AbstractSelection<BookingIntervalChangeSelection> {
    @Override
    protected Uri baseUri() {
        return BookingIntervalChangeColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingIntervalChangeCursor} object, which is positioned before the first entry, or null.
     */
    public BookingIntervalChangeCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingIntervalChangeCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingIntervalChangeCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingIntervalChangeCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingIntervalChangeSelection id(long... value) {
        addEquals("booking_interval_change." + BookingIntervalChangeColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalId(long... value) {
        addEquals(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIdNot(long... value) {
        addNotEquals(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIdGt(long value) {
        addGreaterThan(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIdLt(long value) {
        addLessThan(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalChangeColumns.BOOKING_INTERVAL_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountId(long... value) {
        addEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountIdLt(long value) {
        addLessThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryId(long... value) {
        addEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIdLt(long value) {
        addLessThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalComment(String... value) {
        addEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCommentNot(String... value) {
        addNotEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCommentLike(String... value) {
        addLike(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCommentContains(String... value) {
        addContains(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCommentStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalCommentEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalInterval(String... value) {
        addEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIntervalNot(String... value) {
        addNotEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIntervalLike(String... value) {
        addLike(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIntervalContains(String... value) {
        addContains(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIntervalStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalIntervalEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStart(Date... value) {
        addEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStartNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStart(long... value) {
        addEquals(BookingIntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStartAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStartAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStartBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateStartBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEnd(Date... value) {
        addEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEndNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEnd(long... value) {
        addEquals(BookingIntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEndAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEndAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEndBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateEndBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLast(Date... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLastNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLast(long... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLastAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLastAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLastBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateLastBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntil(Date... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntilNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntil(long... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntilAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntilBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirection(String... value) {
        addEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirectionNot(String... value) {
        addNotEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirectionLike(String... value) {
        addLike(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirectionContains(String... value) {
        addContains(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirectionStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalDirectionEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmount(int... value) {
        addEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmountNot(int... value) {
        addNotEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmountGt(int value) {
        addGreaterThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmountGtEq(int value) {
        addGreaterThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmountLt(int value) {
        addLessThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection bookingIntervalAmountLtEq(int value) {
        addLessThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection followUp(boolean value) {
        addEquals(BookingIntervalChangeColumns.FOLLOW_UP, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection date(Date... value) {
        addEquals(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection dateNot(Date... value) {
        addNotEquals(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection date(long... value) {
        addEquals(BookingIntervalChangeColumns.DATE, toObjectArray(value));
        return this;
    }

    public BookingIntervalChangeSelection dateAfter(Date value) {
        addGreaterThan(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection dateAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection dateBefore(Date value) {
        addLessThan(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection dateBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalChangeColumns.DATE, value);
        return this;
    }

    public BookingIntervalChangeSelection comment(String... value) {
        addEquals(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection commentNot(String... value) {
        addNotEquals(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection commentLike(String... value) {
        addLike(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection commentContains(String... value) {
        addContains(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection commentStartsWith(String... value) {
        addStartsWith(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection commentEndsWith(String... value) {
        addEndsWith(BookingIntervalChangeColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalChangeSelection amount(Integer... value) {
        addEquals(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection amountNot(Integer... value) {
        addNotEquals(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection amountGt(int value) {
        addGreaterThan(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection amountGtEq(int value) {
        addGreaterThanOrEquals(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection amountLt(int value) {
        addLessThan(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalChangeSelection amountLtEq(int value) {
        addLessThanOrEquals(BookingIntervalChangeColumns.AMOUNT, value);
        return this;
    }
}
