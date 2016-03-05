package de.nenick.quacc.database.bookinginterval;

import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;

public class BookingIntervalSpecAll extends BookingIntervalSpec {

    @Override
    public BookingIntervalSelection toSelection() {
        return new BookingIntervalSelection();
    }
}
