package de.nenick.quacc.database.provider.bookingtemplate;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_template} table.
 */
public class BookingTemplateSelection extends AbstractSelection<BookingTemplateSelection> {
    @Override
    protected Uri baseUri() {
        return BookingTemplateColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingTemplateCursor} object, which is positioned before the first entry, or null.
     */
    public BookingTemplateCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingTemplateCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingTemplateCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingTemplateCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingTemplateSelection id(long... value) {
        addEquals("booking_template." + BookingTemplateColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection accountId(long... value) {
        addEquals(BookingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection accountIdNot(long... value) {
        addNotEquals(BookingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection accountIdGt(long value) {
        addGreaterThan(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateSelection accountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateSelection accountIdLt(long value) {
        addLessThan(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateSelection accountIdLtEq(long value) {
        addLessThanOrEquals(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateSelection accountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection accountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection accountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection accountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateSelection accountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateSelection accountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateSelection accountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateSelection categoryId(long... value) {
        addEquals(BookingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection categoryIdNot(long... value) {
        addNotEquals(BookingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection categoryIdGt(long value) {
        addGreaterThan(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateSelection categoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateSelection categoryIdLt(long value) {
        addLessThan(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateSelection categoryIdLtEq(long value) {
        addLessThanOrEquals(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateSelection categoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateSelection categorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection categoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection categoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection categoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingTemplateSelection categoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateSelection categoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateSelection categoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateSelection categoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateSelection comment(String... value) {
        addEquals(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection commentNot(String... value) {
        addNotEquals(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection commentLike(String... value) {
        addLike(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection commentContains(String... value) {
        addContains(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection commentStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection commentEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateSelection interval(String... value) {
        addEquals(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection intervalNot(String... value) {
        addNotEquals(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection intervalLike(String... value) {
        addLike(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection intervalContains(String... value) {
        addContains(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection intervalStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection intervalEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateSelection direction(String... value) {
        addEquals(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection directionNot(String... value) {
        addNotEquals(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection directionLike(String... value) {
        addLike(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection directionContains(String... value) {
        addContains(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection directionStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateSelection directionEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.DIRECTION, value);
        return this;
    }
}
