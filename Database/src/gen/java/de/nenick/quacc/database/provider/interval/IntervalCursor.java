package de.nenick.quacc.database.provider.interval;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.account.*;
import de.nenick.quacc.database.provider.category.*;

/**
 * Cursor wrapper for the {@code interval} table.
 */
public class IntervalCursor extends AbstractCursor implements IntervalModel {
    public IntervalCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(IntervalColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code account_id} value.
     */
    public long getAccountId() {
        Long res = getLongOrNull(IntervalColumns.ACCOUNT_ID);
        if (res == null)
            throw new NullPointerException("The value of 'account_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * The name of the account set by the user.
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
     * The base to calculate the current amount of money. Values are stored in 100 cent.
     */
    public int getAccountInitialvalue() {
        Integer res = getIntegerOrNull(AccountColumns.INITIALVALUE);
        if (res == null)
            throw new NullPointerException("The value of 'initialvalue' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code category_id} value.
     */
    public long getCategoryId() {
        Long res = getLongOrNull(IntervalColumns.CATEGORY_ID);
        if (res == null)
            throw new NullPointerException("The value of 'category_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code name} value.
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
     * Get the {@code section} value.
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
     * Get the {@code interval} value.
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
     * Get the {@code type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getCategoryType() {
        String res = getStringOrNull(CategoryColumns.TYPE);
        if (res == null)
            throw new NullPointerException("The value of 'type' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code level} value.
     */
    public int getCategoryLevel() {
        Integer res = getIntegerOrNull(CategoryColumns.LEVEL);
        if (res == null)
            throw new NullPointerException("The value of 'level' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code comment} value.
     * Can be {@code null}.
     */
    @Nullable
    public String getComment() {
        String res = getStringOrNull(IntervalColumns.COMMENT);
        return res;
    }

    /**
     * The type of this interval how it will add accounting.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getInterval() {
        String res = getStringOrNull(IntervalColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Start date when the interval have the first accounting.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateStart() {
        Date res = getDateOrNull(IntervalColumns.DATE_START);
        if (res == null)
            throw new NullPointerException("The value of 'date_start' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Last date where the interval is active. This must not match the last accounting date.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateEnd() {
        Date res = getDateOrNull(IntervalColumns.DATE_END);
        if (res == null)
            throw new NullPointerException("The value of 'date_end' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * This is the last account entry created by this interval.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateLast() {
        Date res = getDateOrNull(IntervalColumns.DATE_LAST);
        if (res == null)
            throw new NullPointerException("The value of 'date_last' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Until this date all accounting which belong to this interval are created or updated.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateUpdatedUntil() {
        Date res = getDateOrNull(IntervalColumns.DATE_UPDATED_UNTIL);
        if (res == null)
            throw new NullPointerException("The value of 'date_updated_until' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Get the {@code type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getType() {
        String res = getStringOrNull(IntervalColumns.TYPE);
        if (res == null)
            throw new NullPointerException("The value of 'type' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Values are stored in 100 cent.
     */
    public int getValue() {
        Integer res = getIntegerOrNull(IntervalColumns.VALUE);
        if (res == null)
            throw new NullPointerException("The value of 'value' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
