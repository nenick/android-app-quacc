package de.nenick.quacc.database.bookinginterval;

import android.support.annotation.Nullable;

import java.util.Date;

import de.nenick.quacc.database.DbConstantValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;

public class BookingIntervalSpecByRange extends BookingIntervalSpec {

    private final long accountId;
    private final Date startDate;
    private final Date endDate;

    public BookingIntervalSpecByRange(long accountId, Date startDate, Date endDate) {
        this.accountId = accountId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public BookingIntervalSelection toSelection() {
        return new BookingIntervalSelection()
                .accountId(accountId)
                .and().dateStartAfterEq(startDate)
                .and().dateEndBeforeEq(endDate).or().dateEnd(DbConstantValues.NO_DATE_GIVEN);
    }

    @Nullable
    @Override
    public String sortBy() {
        return BookingIntervalColumns.DATE_START;
    }
}
