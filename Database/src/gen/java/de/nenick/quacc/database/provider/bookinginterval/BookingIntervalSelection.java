package de.nenick.quacc.database.provider.bookinginterval;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_interval} table.
 */
public class BookingIntervalSelection extends AbstractSelection<BookingIntervalSelection> {
    @Override
    protected Uri baseUri() {
        return BookingIntervalColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingIntervalCursor} object, which is positioned before the first entry, or null.
     */
    public BookingIntervalCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingIntervalCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingIntervalCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingIntervalCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingIntervalSelection id(long... value) {
        addEquals("booking_interval." + BookingIntervalColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection accountId(long... value) {
        addEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection accountIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection accountIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalSelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalSelection accountIdLt(long value) {
        addLessThan(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalSelection accountIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingIntervalSelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection accountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection accountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection accountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalSelection accountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalSelection accountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalSelection accountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingIntervalSelection categoryId(long... value) {
        addEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection categoryIdNot(long... value) {
        addNotEquals(BookingIntervalColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection categoryIdGt(long value) {
        addGreaterThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalSelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalSelection categoryIdLt(long value) {
        addLessThan(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalSelection categoryIdLtEq(long value) {
        addLessThanOrEquals(BookingIntervalColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingIntervalSelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingIntervalSelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection categoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection categoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection categoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection categoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalSelection categoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalSelection categoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalSelection categoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingIntervalSelection comment(String... value) {
        addEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection commentNot(String... value) {
        addNotEquals(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection commentLike(String... value) {
        addLike(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection commentContains(String... value) {
        addContains(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection commentStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection commentEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.COMMENT, value);
        return this;
    }

    public BookingIntervalSelection interval(String... value) {
        addEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection intervalNot(String... value) {
        addNotEquals(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection intervalLike(String... value) {
        addLike(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection intervalContains(String... value) {
        addContains(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection intervalStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection intervalEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.INTERVAL, value);
        return this;
    }

    public BookingIntervalSelection dateStart(Date... value) {
        addEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateStartNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateStart(long... value) {
        addEquals(BookingIntervalColumns.DATE_START, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection dateStartAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateStartAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateStartBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateStartBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_START, value);
        return this;
    }

    public BookingIntervalSelection dateEnd(Date... value) {
        addEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateEndNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateEnd(long... value) {
        addEquals(BookingIntervalColumns.DATE_END, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection dateEndAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateEndAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateEndBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateEndBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_END, value);
        return this;
    }

    public BookingIntervalSelection dateLast(Date... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateLastNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateLast(long... value) {
        addEquals(BookingIntervalColumns.DATE_LAST, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection dateLastAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateLastAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateLastBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateLastBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_LAST, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntil(Date... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntilNot(Date... value) {
        addNotEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntil(long... value) {
        addEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntilAfter(Date value) {
        addGreaterThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntilAfterEq(Date value) {
        addGreaterThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntilBefore(Date value) {
        addLessThan(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection dateUpdatedUntilBeforeEq(Date value) {
        addLessThanOrEquals(BookingIntervalColumns.DATE_UPDATED_UNTIL, value);
        return this;
    }

    public BookingIntervalSelection direction(String... value) {
        addEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection directionNot(String... value) {
        addNotEquals(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection directionLike(String... value) {
        addLike(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection directionContains(String... value) {
        addContains(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection directionStartsWith(String... value) {
        addStartsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection directionEndsWith(String... value) {
        addEndsWith(BookingIntervalColumns.DIRECTION, value);
        return this;
    }

    public BookingIntervalSelection amount(int... value) {
        addEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection amountNot(int... value) {
        addNotEquals(BookingIntervalColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingIntervalSelection amountGt(int value) {
        addGreaterThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalSelection amountGtEq(int value) {
        addGreaterThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalSelection amountLt(int value) {
        addLessThan(BookingIntervalColumns.AMOUNT, value);
        return this;
    }

    public BookingIntervalSelection amountLtEq(int value) {
        addLessThanOrEquals(BookingIntervalColumns.AMOUNT, value);
        return this;
    }
}
