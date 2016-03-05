package de.nenick.quacc.database.bookingintervalchange;

import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;

public class BookingIntervalChangeSpecByIntervalId extends BookingIntervalChangeSpec {

    private long intervalId;

    public BookingIntervalChangeSpecByIntervalId(long intervalId) {
        this.intervalId = intervalId;
    }

    @Override
    public BookingIntervalChangeSelection toSelection() {
        return new BookingIntervalChangeSelection().bookingIntervalId(intervalId);
    }
}
