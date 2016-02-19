package de.nenick.quacc.database.provider.bookingintervalentry;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookinginterval.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;
import de.nenick.quacc.database.provider.bookingentry.*;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Cursor wrapper for the {@code booking_interval_entry} table.
 */
public class BookingIntervalEntryCursor extends AbstractCursor implements BookingIntervalEntryModel {
    public BookingIntervalEntryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(BookingIntervalEntryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced booking interval.
     */
    public long getBookingIntervalId() {
        Long res = getLongOrNull(BookingIntervalEntryColumns.BOOKING_INTERVAL_ID);
        if (res == null)
            throw new NullPointerException("The value of 'booking_interval_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced account.
     */
    public long getBookingIntervalAccountId() {
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
    public String getBookingIntervalAccountName() {
        String res = getStringOrNull(AccountColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
     */
    public int getBookingIntervalAccountInitialvalue() {
        Integer res = getIntegerOrNull(AccountColumns.INITIALVALUE);
        if (res == null)
            throw new NullPointerException("The value of 'initialvalue' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced category.
     */
    public long getBookingIntervalCategoryId() {
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
    public String getBookingIntervalCategoryName() {
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
    public String getBookingIntervalCategorySection() {
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
    public String getBookingIntervalCategoryInterval() {
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
    public String getBookingIntervalCategoryDirection() {
        String res = getStringOrNull(CategoryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public int getBookingIntervalCategoryLevel() {
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
    public String getBookingIntervalComment() {
        String res = getStringOrNull(BookingIntervalColumns.COMMENT);
        return res;
    }

    /**
     * Strategy how to repeat.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingIntervalInterval() {
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
    public Date getBookingIntervalDateStart() {
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
    public Date getBookingIntervalDateEnd() {
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
    public Date getBookingIntervalDateLast() {
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
    public Date getBookingIntervalDateUpdatedUntil() {
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
    public String getBookingIntervalDirection() {
        String res = getStringOrNull(BookingIntervalColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Values are stored in cents.
     */
    public int getBookingIntervalAmount() {
        Integer res = getIntegerOrNull(BookingIntervalColumns.AMOUNT);
        if (res == null)
            throw new NullPointerException("The value of 'amount' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced booking entry.
     */
    public long getBookingEntryId() {
        Long res = getLongOrNull(BookingIntervalEntryColumns.BOOKING_ENTRY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'booking_entry_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced account.
     */
    public long getBookingEntryAccountId() {
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
    public String getBookingEntryAccountName() {
        String res = getStringOrNull(AccountColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
     */
    public int getBookingEntryAccountInitialvalue() {
        Integer res = getIntegerOrNull(AccountColumns.INITIALVALUE);
        if (res == null)
            throw new NullPointerException("The value of 'initialvalue' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Referenced category.
     */
    public long getBookingEntryCategoryId() {
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
    public String getBookingEntryCategoryName() {
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
    public String getBookingEntryCategorySection() {
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
    public String getBookingEntryCategoryInterval() {
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
    public String getBookingEntryCategoryDirection() {
        String res = getStringOrNull(CategoryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public int getBookingEntryCategoryLevel() {
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
    public String getBookingEntryComment() {
        String res = getStringOrNull(BookingEntryColumns.COMMENT);
        return res;
    }

    /**
     * Entry was created by this interval strategy.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getBookingEntryInterval() {
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
    public Date getBookingEntryDate() {
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
    public String getBookingEntryDirection() {
        String res = getStringOrNull(BookingEntryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Values are stored in cents.
     */
    public int getBookingEntryAmount() {
        Integer res = getIntegerOrNull(BookingEntryColumns.AMOUNT);
        if (res == null)
            throw new NullPointerException("The value of 'amount' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
