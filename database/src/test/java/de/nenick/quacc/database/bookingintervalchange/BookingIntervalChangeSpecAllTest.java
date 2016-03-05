package de.nenick.quacc.database.bookingintervalchange;

import org.junit.Test;

public class BookingIntervalChangeSpecAllTest extends BookingIntervalChangeTestBase {

    BookingIntervalChangeSpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingIntervalChangeSpecAll();
        result = bookingIntervalChangeRepository.query(spec);
    }
}