package de.nenick.quacc.database.bookingintervalchange;

import org.junit.Test;

public class BookingIntervalChangeSpecByIntervalIdTest extends BookingIntervalChangeTestBase {

    BookingIntervalChangeSpecByIntervalId spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long intervalId = created.get(0).getBookingIntervalId();
        whenQuery(intervalId);
        thenQueryResultCountIs(2);
    }

    private void whenQuery(long id) {
        spec = new BookingIntervalChangeSpecByIntervalId(id);
        result = bookingIntervalChangeRepository.query(spec);
    }

}