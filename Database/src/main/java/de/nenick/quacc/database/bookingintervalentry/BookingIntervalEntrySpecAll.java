package de.nenick.quacc.database.bookingintervalentry;

import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntrySelection;

public class BookingIntervalEntrySpecAll extends BookingIntervalEntrySpec {

    @Override
    public BookingIntervalEntrySelection toSelection() {
        return new BookingIntervalEntrySelection();
    }
}
