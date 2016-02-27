package de.nenick.quacc.database.bookingentry;

import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

public class BookingEntrySpecCategorySummeryByRange extends BookingEntrySpec {

    private final long accountId;
    private final Date startDate;
    private final Date endDate;

    public BookingEntrySpecCategorySummeryByRange(long accountId, Date startDate, Date endDate) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection()
                .accountId(accountId)
                .and().dateAfterEq(startDate)
                .and().dateBeforeEq(endDate)
                .groupBy(BookingEntryColumns.CATEGORY_ID + " , " + BookingEntryColumns.DIRECTION);
    }

    @Nullable
    @Override
    public String[] projection() {
        String[] projection = {CategoryColumns.NAME, BookingEntryColumns.CATEGORY_ID, BookingEntryColumns.DIRECTION, BookingEntryColumns._ID, BookingEntryColumns._ID, "MIN(" + BookingEntryColumns.DATE +") AS minDate", "MAX(" + BookingEntryColumns.DATE +") AS " + BookingEntryColumns.DATE, "SUM(" + BookingEntryColumns.AMOUNT + ") AS " + BookingEntryColumns.AMOUNT};
        return projection;
        /*return new String[]{"MIN(" + BookingEntryColumns.DATE + ") AS minDate",
                "MAX(" + BookingEntryColumns.DATE + ") AS " + BookingEntryColumns.DATE,
                "SUM(" + BookingEntryColumns.AMOUNT + ") AS " + BookingEntryColumns.AMOUNT};
                */
    }

    @Nullable
    @Override
    public String sortBy() {
        return BookingEntryColumns.DATE;
    }
}
