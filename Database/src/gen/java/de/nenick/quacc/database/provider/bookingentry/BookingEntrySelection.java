package de.nenick.quacc.database.provider.bookingentry;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_entry} table.
 */
public class BookingEntrySelection extends AbstractSelection<BookingEntrySelection> {
    @Override
    protected Uri baseUri() {
        return BookingEntryColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingEntryCursor} object, which is positioned before the first entry, or null.
     */
    public BookingEntryCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingEntryCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingEntryCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingEntryCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingEntrySelection id(long... value) {
        addEquals("booking_entry." + BookingEntryColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection accountId(long... value) {
        addEquals(BookingEntryColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection accountIdNot(long... value) {
        addNotEquals(BookingEntryColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection accountIdGt(long value) {
        addGreaterThan(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingEntrySelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingEntrySelection accountIdLt(long value) {
        addLessThan(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingEntrySelection accountIdLtEq(long value) {
        addLessThanOrEquals(BookingEntryColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingEntrySelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection accountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection accountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection accountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingEntrySelection accountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingEntrySelection accountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingEntrySelection accountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingEntrySelection categoryId(long... value) {
        addEquals(BookingEntryColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection categoryIdNot(long... value) {
        addNotEquals(BookingEntryColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection categoryIdGt(long value) {
        addGreaterThan(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingEntrySelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingEntrySelection categoryIdLt(long value) {
        addLessThan(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingEntrySelection categoryIdLtEq(long value) {
        addLessThanOrEquals(BookingEntryColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingEntrySelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingEntrySelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingEntrySelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection categoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection categoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection categoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection categoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingEntrySelection categoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingEntrySelection categoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingEntrySelection categoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingEntrySelection comment(String... value) {
        addEquals(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection commentNot(String... value) {
        addNotEquals(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection commentLike(String... value) {
        addLike(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection commentContains(String... value) {
        addContains(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection commentStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection commentEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.COMMENT, value);
        return this;
    }

    public BookingEntrySelection interval(String... value) {
        addEquals(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection intervalNot(String... value) {
        addNotEquals(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection intervalLike(String... value) {
        addLike(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection intervalContains(String... value) {
        addContains(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection intervalStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection intervalEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.INTERVAL, value);
        return this;
    }

    public BookingEntrySelection date(Date... value) {
        addEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection dateNot(Date... value) {
        addNotEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection date(long... value) {
        addEquals(BookingEntryColumns.DATE, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection dateAfter(Date value) {
        addGreaterThan(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection dateAfterEq(Date value) {
        addGreaterThanOrEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection dateBefore(Date value) {
        addLessThan(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection dateBeforeEq(Date value) {
        addLessThanOrEquals(BookingEntryColumns.DATE, value);
        return this;
    }

    public BookingEntrySelection direction(String... value) {
        addEquals(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection directionNot(String... value) {
        addNotEquals(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection directionLike(String... value) {
        addLike(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection directionContains(String... value) {
        addContains(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection directionStartsWith(String... value) {
        addStartsWith(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection directionEndsWith(String... value) {
        addEndsWith(BookingEntryColumns.DIRECTION, value);
        return this;
    }

    public BookingEntrySelection amount(int... value) {
        addEquals(BookingEntryColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection amountNot(int... value) {
        addNotEquals(BookingEntryColumns.AMOUNT, toObjectArray(value));
        return this;
    }

    public BookingEntrySelection amountGt(int value) {
        addGreaterThan(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingEntrySelection amountGtEq(int value) {
        addGreaterThanOrEquals(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingEntrySelection amountLt(int value) {
        addLessThan(BookingEntryColumns.AMOUNT, value);
        return this;
    }

    public BookingEntrySelection amountLtEq(int value) {
        addLessThanOrEquals(BookingEntryColumns.AMOUNT, value);
        return this;
    }
}
