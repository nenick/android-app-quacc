package de.nenick.quacc.database.bookingintervalchange;

import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;

public class BookingIntervalChangeSpecByInterval extends BookingIntervalChangeSpec {

    private long intervalId;

    public BookingIntervalChangeSpecByInterval(long intervalId) {
        this.intervalId = intervalId;
    }

    @Override
    public BookingIntervalChangeSelection toSelection() {
        return new BookingIntervalChangeSelection().bookingIntervalId(intervalId);
    }
}
