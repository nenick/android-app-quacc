package de.nenick.quacc.database.bookingintervalchange;

import org.junit.Test;

public class BookingIntervalChangeSpecByIntervalTest extends BookingIntervalChangeTestBase {

    BookingIntervalChangeSpecByInterval spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long intervalId = created.get(0).getBookingIntervalId();
        whenQuery(intervalId);
        thenQueryResultCountIs(2);
    }

    private void whenQuery(long id) {
        spec = new BookingIntervalChangeSpecByInterval(id);
        result = bookingIntervalChangeRepository.query(spec);
    }

}