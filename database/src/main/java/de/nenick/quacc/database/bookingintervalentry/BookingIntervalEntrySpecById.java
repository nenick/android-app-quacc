package de.nenick.quacc.database.bookingintervalentry;

import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntrySelection;

public class BookingIntervalEntrySpecById extends BookingIntervalEntrySpec {

    private long id;

    public BookingIntervalEntrySpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingIntervalEntrySelection toSelection() {
        return new BookingIntervalEntrySelection().id(id);
    }
}
