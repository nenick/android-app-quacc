package de.nenick.quacc.database.provider.bookingtemplatekeyword;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.bookingtemplate.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code booking_template_keyword} table.
 */
public class BookingTemplateKeywordSelection extends AbstractSelection<BookingTemplateKeywordSelection> {
    @Override
    protected Uri baseUri() {
        return BookingTemplateKeywordColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code BookingTemplateKeywordCursor} object, which is positioned before the first entry, or null.
     */
    public BookingTemplateKeywordCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new BookingTemplateKeywordCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public BookingTemplateKeywordCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public BookingTemplateKeywordCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public BookingTemplateKeywordSelection id(long... value) {
        addEquals("booking_template_keyword." + BookingTemplateKeywordColumns._ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection text(String... value) {
        addEquals(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection textNot(String... value) {
        addNotEquals(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection textLike(String... value) {
        addLike(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection textContains(String... value) {
        addContains(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection textStartsWith(String... value) {
        addStartsWith(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection textEndsWith(String... value) {
        addEndsWith(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateId(long... value) {
        addEquals(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIdNot(long... value) {
        addNotEquals(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIdGt(long value) {
        addGreaterThan(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIdGtEq(long value) {
        addGreaterThanOrEquals(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIdLt(long value) {
        addLessThan(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIdLtEq(long value) {
        addLessThanOrEquals(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountId(long... value) {
        addEquals(BookingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountIdNot(long... value) {
        addNotEquals(BookingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountIdGt(long value) {
        addGreaterThan(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountIdGtEq(long value) {
        addGreaterThanOrEquals(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountIdLt(long value) {
        addLessThan(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountIdLtEq(long value) {
        addLessThanOrEquals(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryId(long... value) {
        addEquals(BookingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIdNot(long... value) {
        addNotEquals(BookingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIdGt(long value) {
        addGreaterThan(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIdLt(long value) {
        addLessThan(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIdLtEq(long value) {
        addLessThanOrEquals(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirection(String... value) {
        addEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirectionNot(String... value) {
        addNotEquals(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirectionLike(String... value) {
        addLike(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirectionContains(String... value) {
        addContains(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryDirectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateComment(String... value) {
        addEquals(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCommentNot(String... value) {
        addNotEquals(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCommentLike(String... value) {
        addLike(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCommentContains(String... value) {
        addContains(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCommentStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateCommentEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateInterval(String... value) {
        addEquals(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIntervalNot(String... value) {
        addNotEquals(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIntervalLike(String... value) {
        addLike(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIntervalContains(String... value) {
        addContains(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIntervalStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateIntervalEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.INTERVAL, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirection(String... value) {
        addEquals(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirectionNot(String... value) {
        addNotEquals(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirectionLike(String... value) {
        addLike(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirectionContains(String... value) {
        addContains(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirectionStartsWith(String... value) {
        addStartsWith(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

    public BookingTemplateKeywordSelection bookingTemplateDirectionEndsWith(String... value) {
        addEndsWith(BookingTemplateColumns.DIRECTION, value);
        return this;
    }
}
