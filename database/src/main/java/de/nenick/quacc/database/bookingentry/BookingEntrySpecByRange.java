package de.nenick.quacc.database.bookingentry;

import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;

public class BookingEntrySpecByRange extends BookingEntrySpec {

    private final long account;
    private final Date startDate;
    private final Date endDate;

    public BookingEntrySpecByRange(long account, Date startDate, Date endDate) {
        this.account = account;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection()
                .accountId(account)
                .and().dateAfterEq(startDate)
                .and().dateBeforeEq(endDate);
    }

    @Nullable
    @Override
    public String sortBy() {
        return BookingEntryColumns.DATE;
    }
}
