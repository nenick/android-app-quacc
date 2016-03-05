package de.nenick.quacc.database.provider.bookingentry;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Cursor wrapper for the {@code booking_entry} table.
 */
public class BookingEntryCursor extends AbstractCursor implements BookingEntryModel {
    public BookingEntryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(BookingEntryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced account.
     */
    public long getAccountId() {
        Long res = getLongOrNull(BookingEntryColumns.ACCOUNT_ID);
        if (res == null)
            throw new NullPointerException("The value of 'account_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The name of the account.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getAccountName() {
        String res = getStringOrNull(AccountColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
     */
    public int getAccountInitialvalue() {
        Integer res = getIntegerOrNull(AccountColumns.INITIALVALUE);
        if (res == null)
            throw new NullPointerException("The value of 'initialvalue' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced category.
     */
    public long getCategoryId() {
        Long res = getLongOrNull(BookingEntryColumns.CATEGORY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'category_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCategoryName() {
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
    public String getCategorySection() {
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
    public String getCategoryInterval() {
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
    public String getCategoryDirection() {
        String res = getStringOrNull(CategoryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public int getCategoryLevel() {
        Integer res = getIntegerOrNull(CategoryColumns.LEVEL);
        if (res == null)
            throw new NullPointerException("The value of 'level' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Optional description for this entry.
     * Can be {@code null}.
     */
    @Nullable
    public String getComment() {
        String res = getStringOrNull(BookingEntryColumns.COMMENT);
        return res;
    }

    /**
     * Entry was created by this interval strategy.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getInterval() {
        String res = getStringOrNull(BookingEntryColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Booking is done at this date. May be past or future.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDate() {
        Date res = getDateOrNull(BookingEntryColumns.DATE);
        if (res == null)
            throw new NullPointerException("The value of 'date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Defines how this booking entry will effect the account money.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDirection() {
        String res = getStringOrNull(BookingEntryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Values are stored in cents.
     */
    public int getAmount() {
        Integer res = getIntegerOrNull(BookingEntryColumns.AMOUNT);
        if (res == null)
            throw new NullPointerException("The value of 'amount' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
