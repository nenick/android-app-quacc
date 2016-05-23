package de.nenick.quacc.database.bookingentry;

import android.database.Cursor;
import android.support.annotation.NonNull;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;

// TODO test for failure when switched DATE / MIN_DATE
public class CategorySummeryCursor extends BookingEntryCursor {

    public static String MIN_DATE = "minDate";

    public CategorySummeryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Booking is done at this date. May be past or future.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateStart() {
        Date res = getDateOrNull(MIN_DATE);
        if (res == null)
            throw new NullPointerException("The value of 'date' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Booking is done at this date. May be past or future.
     * Cannot be {@code null}.
     */
    @NonNull
    public Date getDateEnd() {
        Date res = getDateOrNull(BookingEntryColumns.DATE);
        if (res == null)
            throw new NullPointerException("The value of 'date' in the database was null, which is not allowed according to the model definition");
        return res;
    }


}
