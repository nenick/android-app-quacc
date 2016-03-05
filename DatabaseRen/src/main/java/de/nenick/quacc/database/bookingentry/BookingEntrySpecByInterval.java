package de.nenick.quacc.database.bookingentry;

import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;

public class BookingEntrySpecByInterval extends BookingEntrySpec {

    private String interval;

    public BookingEntrySpecByInterval(String interval) {
        this.interval = interval;
    }

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection().interval(interval);
    }
}
