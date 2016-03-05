package de.nenick.quacc.database.provider.bookingtemplatekeyword;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookingtemplate.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Cursor wrapper for the {@code booking_template_keyword} table.
 */
public class BookingTemplateKeywordCursor extends AbstractCursor implements BookingTemplateKeywordModel {
    public BookingTemplateKeywordCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(BookingTemplateKeywordColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Word or phrase to match template.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getText() {
        String res = getStringOrNull(BookingTemplateKeywordColumns.TEXT);
        if (res == null)
            throw new NullPointerException("The value of 'text' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced booking template.
     */
    public long getBookingTemplateId() {
        Long res = getLongOrNull(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID);
        if (res == null)
            throw new NullPointerException("The value of 'booking_template_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced account.
     */
    public long getBookingTemplateAccountId() {
        Long res = getLongOrNull(BookingTemplateColumns.ACCOUNT_ID);
        if (res == null)
            throw new NullPointerException("The value of 'account_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The name of the account.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateAccountName() {
        String res = getStringOrNull(AccountColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
     */
    public int getBookingTemplateAccountInitialvalue() {
        Integer res = getIntegerOrNull(AccountColumns.INITIALVALUE);
        if (res == null)
            throw new NullPointerException("The value of 'initialvalue' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced category.
     */
    public long getBookingTemplateCategoryId() {
        Long res = getLongOrNull(BookingTemplateColumns.CATEGORY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'category_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateCategoryName() {
        String res = getStringOrNull(CategoryColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Main group of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateCategorySection() {
        String res = getStringOrNull(CategoryColumns.SECTION);
        if (res == null)
            throw new NullPointerException("The value of 'section' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Possible booking interval for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateCategoryInterval() {
        String res = getStringOrNull(CategoryColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Possible booking direction for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateCategoryDirection() {
        String res = getStringOrNull(CategoryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public int getBookingTemplateCategoryLevel() {
        Integer res = getIntegerOrNull(CategoryColumns.LEVEL);
        if (res == null)
            throw new NullPointerException("The value of 'level' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Optional description for this template.
     * Can be {@code null}.
     */
    @Nullable
    public String getBookingTemplateComment() {
        String res = getStringOrNull(BookingTemplateColumns.COMMENT);
        return res;
    }

    /**
     * Possible booking interval for this template.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateInterval() {
        String res = getStringOrNull(BookingTemplateColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Possible booking direction for this template.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingTemplateDirection() {
        String res = getStringOrNull(BookingTemplateColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
