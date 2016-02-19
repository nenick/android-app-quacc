package de.nenick.quacc.database.provider.bookinginterval;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Cursor wrapper for the {@code booking_interval} table.
 */
public class BookingIntervalCursor extends AbstractCursor implements BookingIntervalModel {
    public BookingIntervalCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(BookingIntervalColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced account.
     */
    public long getAccountId() {
        Long res = getLongOrNull(BookingIntervalColumns.ACCOUNT_ID);
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
        Long res = getLongOrNull(BookingIntervalColumns.CATEGORY_ID);
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
     * Optional description for booking entries.
     * Can be {@code null}.
     */
    @Nullable
    public String getComment() {
        String res = getStringOrNull(BookingIntervalColumns.COMMENT);
        return res;
    }

    /**
     * Strategy how to repeat.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getInterval() {
        String res = getStringOrNull(BookingIntervalColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Date when the interval creates the first booking entry.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateStart() {
        Date res = getDateOrNull(BookingIntervalColumns.DATE_START);
        if (res == null)
            throw new NullPointerException("The value of 'date_start' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Date when the interval create no more booking entries. This must not match the last created booking date.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateEnd() {
        Date res = getDateOrNull(BookingIntervalColumns.DATE_END);
        if (res == null)
            throw new NullPointerException("The value of 'date_end' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * This is the last booking entry created by this interval.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateLast() {
        Date res = getDateOrNull(BookingIntervalColumns.DATE_LAST);
        if (res == null)
            throw new NullPointerException("The value of 'date_last' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Necessary booking entries are created or updated until this date.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateUpdatedUntil() {
        Date res = getDateOrNull(BookingIntervalColumns.DATE_UPDATED_UNTIL);
        if (res == null)
            throw new NullPointerException("The value of 'date_updated_until' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * direction how booking entries are created.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDirection() {
        String res = getStringOrNull(BookingIntervalColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Values are stored in cents.
     */
    public int getAmount() {
        Integer res = getIntegerOrNull(BookingIntervalColumns.AMOUNT);
        if (res == null)
            throw new NullPointerException("The value of 'amount' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
