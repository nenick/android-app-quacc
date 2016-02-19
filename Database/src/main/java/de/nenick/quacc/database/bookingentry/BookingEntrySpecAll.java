package de.nenick.quacc.database.bookingentry;

import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;

public class BookingEntrySpecAll extends BookingEntrySpec {

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection();
    }
}
