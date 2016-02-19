package de.nenick.quacc.database.bookingentry;

import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;

public class BookingEntrySpecCategoryEntriesByRange extends BookingEntrySpec {

    private final long accountId;
    private final Date startDate;
    private final Date endDate;
    private final long categoryId;
    private final String direction;

    public BookingEntrySpecCategoryEntriesByRange(long accountId, Date startDate, Date endDate, long categoryId, String direction) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.categoryId = categoryId;
        this.direction = direction;
    }

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection()
                .accountId(accountId)
                .and().dateAfterEq(startDate)
                .and().dateBeforeEq(endDate)
                .and().categoryId(categoryId)
                .and().direction(direction);
    }

    @Nullable
    @Override
    public String sortBy() {
        return BookingEntryColumns.DATE;
    }

    @Nullable
    @Override
    public String[] projection() {
        return null;
    }
}
