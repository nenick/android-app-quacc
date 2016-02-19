package de.nenick.quacc.database.bookinginterval;

import java.util.Date;

import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;

public class BookingIntervalSpecNeedUpdate extends BookingIntervalSpec {

    private long accountId;
    private final Date updatedUntil;

    public BookingIntervalSpecNeedUpdate(long accountId, Date updatedUntil) {
        this.accountId = accountId;
        this.updatedUntil = updatedUntil;
    }

    @Override
    public BookingIntervalSelection toSelection() {
        return new BookingIntervalSelection()
                .accountId(accountId)
                .and().dateUpdatedUntilBefore(updatedUntil);
    }
}
