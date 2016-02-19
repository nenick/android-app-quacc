package de.nenick.quacc.database.bookingentry;

import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;

public class BookingEntrySpecById extends BookingEntrySpec {

    private final long id;

    public BookingEntrySpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingEntrySelection toSelection() {
        return new BookingEntrySelection().id(id);
    }
}
