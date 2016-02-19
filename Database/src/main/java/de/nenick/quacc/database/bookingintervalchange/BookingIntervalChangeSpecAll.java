package de.nenick.quacc.database.bookingintervalchange;

import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;

public class BookingIntervalChangeSpecAll extends BookingIntervalChangeSpec {

    @Override
    public BookingIntervalChangeSelection toSelection() {
        return new BookingIntervalChangeSelection();
    }
}
