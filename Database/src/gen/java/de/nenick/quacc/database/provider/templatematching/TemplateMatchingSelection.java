package de.nenick.quacc.database.provider.templatematching;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.accountingtemplate.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Selection for the {@code template_matching} table.
 */
public class TemplateMatchingSelection extends AbstractSelection<TemplateMatchingSelection> {
    @Override
    protected Uri baseUri() {
        return TemplateMatchingColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code TemplateMatchingCursor} object, which is positioned before the first entry, or null.
     */
    public TemplateMatchingCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new TemplateMatchingCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null)}.
     */
    public TemplateMatchingCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null)}.
     */
    public TemplateMatchingCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public TemplateMatchingSelection id(long... value) {
        addEquals("template_matching." + TemplateMatchingColumns._ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection text(String... value) {
        addEquals(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection textNot(String... value) {
        addNotEquals(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection textLike(String... value) {
        addLike(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection textContains(String... value) {
        addContains(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection textStartsWith(String... value) {
        addStartsWith(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection textEndsWith(String... value) {
        addEndsWith(TemplateMatchingColumns.TEXT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateId(long... value) {
        addEquals(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIdNot(long... value) {
        addNotEquals(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIdGt(long value) {
        addGreaterThan(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIdGtEq(long value) {
        addGreaterThanOrEquals(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIdLt(long value) {
        addLessThan(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIdLtEq(long value) {
        addLessThanOrEquals(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountId(long... value) {
        addEquals(AccountingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountIdNot(long... value) {
        addNotEquals(AccountingTemplateColumns.ACCOUNT_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountIdGt(long value) {
        addGreaterThan(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountIdLt(long value) {
        addLessThan(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountIdLtEq(long value) {
        addLessThanOrEquals(AccountingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountName(String... value) {
        addEquals(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountNameNot(String... value) {
        addNotEquals(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountNameLike(String... value) {
        addLike(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountNameContains(String... value) {
        addContains(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountNameStartsWith(String... value) {
        addStartsWith(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountNameEndsWith(String... value) {
        addEndsWith(AccountColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalue(int... value) {
        addEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalueNot(int... value) {
        addNotEquals(AccountColumns.INITIALVALUE, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalueGt(int value) {
        addGreaterThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalueGtEq(int value) {
        addGreaterThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalueLt(int value) {
        addLessThan(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateAccountInitialvalueLtEq(int value) {
        addLessThanOrEquals(AccountColumns.INITIALVALUE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryId(long... value) {
        addEquals(AccountingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIdNot(long... value) {
        addNotEquals(AccountingTemplateColumns.CATEGORY_ID, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIdGt(long value) {
        addGreaterThan(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIdGtEq(long value) {
        addGreaterThanOrEquals(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIdLt(long value) {
        addLessThan(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIdLtEq(long value) {
        addLessThanOrEquals(AccountingTemplateColumns.CATEGORY_ID, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryName(String... value) {
        addEquals(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryNameNot(String... value) {
        addNotEquals(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryNameLike(String... value) {
        addLike(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryNameContains(String... value) {
        addContains(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryNameStartsWith(String... value) {
        addStartsWith(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryNameEndsWith(String... value) {
        addEndsWith(CategoryColumns.NAME, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySection(String... value) {
        addEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySectionNot(String... value) {
        addNotEquals(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySectionLike(String... value) {
        addLike(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySectionContains(String... value) {
        addContains(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySectionStartsWith(String... value) {
        addStartsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategorySectionEndsWith(String... value) {
        addEndsWith(CategoryColumns.SECTION, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryInterval(String... value) {
        addEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIntervalNot(String... value) {
        addNotEquals(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIntervalLike(String... value) {
        addLike(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIntervalContains(String... value) {
        addContains(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIntervalStartsWith(String... value) {
        addStartsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryIntervalEndsWith(String... value) {
        addEndsWith(CategoryColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryType(String... value) {
        addEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryTypeNot(String... value) {
        addNotEquals(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryTypeLike(String... value) {
        addLike(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryTypeContains(String... value) {
        addContains(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryTypeStartsWith(String... value) {
        addStartsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryTypeEndsWith(String... value) {
        addEndsWith(CategoryColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevel(int... value) {
        addEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevelNot(int... value) {
        addNotEquals(CategoryColumns.LEVEL, toObjectArray(value));
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevelGt(int value) {
        addGreaterThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevelGtEq(int value) {
        addGreaterThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevelLt(int value) {
        addLessThan(CategoryColumns.LEVEL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCategoryLevelLtEq(int value) {
        addLessThanOrEquals(CategoryColumns.LEVEL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateComment(String... value) {
        addEquals(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCommentNot(String... value) {
        addNotEquals(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCommentLike(String... value) {
        addLike(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCommentContains(String... value) {
        addContains(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCommentStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateCommentEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.COMMENT, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateInterval(String... value) {
        addEquals(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIntervalNot(String... value) {
        addNotEquals(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIntervalLike(String... value) {
        addLike(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIntervalContains(String... value) {
        addContains(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIntervalStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateIntervalEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.INTERVAL, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateType(String... value) {
        addEquals(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateTypeNot(String... value) {
        addNotEquals(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateTypeLike(String... value) {
        addLike(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateTypeContains(String... value) {
        addContains(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateTypeStartsWith(String... value) {
        addStartsWith(AccountingTemplateColumns.TYPE, value);
        return this;
    }

    public TemplateMatchingSelection accountingTemplateTypeEndsWith(String... value) {
        addEndsWith(AccountingTemplateColumns.TYPE, value);
        return this;
    }
}
