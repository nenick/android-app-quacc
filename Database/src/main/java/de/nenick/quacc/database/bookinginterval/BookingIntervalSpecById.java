package de.nenick.quacc.database.bookinginterval;

import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;

public class BookingIntervalSpecById extends BookingIntervalSpec {

    private long id;

    public BookingIntervalSpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingIntervalSelection toSelection() {
        return new BookingIntervalSelection().id(id);
    }
}
