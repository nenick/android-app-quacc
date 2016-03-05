package de.nenick.quacc.database.bookinginterval;

import org.junit.Test;

public class BookingIntervalSpecAllTest extends BookingIntervalTestBase {

    BookingIntervalSpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingIntervalSpecAll();
        result = bookingIntervalRepository.query(spec);
    }
}