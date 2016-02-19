package de.nenick.quacc.database.bookingintervalchange;

import org.junit.Test;

import java.util.Date;

public class BookingIntervalChangeSpecByIntervalUntilDateTest extends BookingIntervalChangeTestBase {

    BookingIntervalChangeSpecByIntervalUntilDate spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long intervalId = created.get(0).getBookingIntervalId();
        Date date = created.get(0).getDate();
        whenQuery(intervalId, date);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id, Date date) {
        spec = new BookingIntervalChangeSpecByIntervalUntilDate(id, date);
        result = bookingIntervalChangeRepository.query(spec);
    }
}