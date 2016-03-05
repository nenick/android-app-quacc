package de.nenick.quacc.database.bookinginterval;

import org.junit.Test;

public class BookingIntervalSpecByIdTest extends BookingIntervalTestBase {

    BookingIntervalSpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingIntervalSpecById(id);
        result = bookingIntervalRepository.query(spec);
    }
}