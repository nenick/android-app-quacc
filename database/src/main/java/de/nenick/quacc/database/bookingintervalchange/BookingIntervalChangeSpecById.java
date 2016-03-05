package de.nenick.quacc.database.bookingintervalchange;

import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;

public class BookingIntervalChangeSpecById extends BookingIntervalChangeSpec {

    private long id;

    public BookingIntervalChangeSpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingIntervalChangeSelection toSelection() {
        return new BookingIntervalChangeSelection().id(id);
    }
}
