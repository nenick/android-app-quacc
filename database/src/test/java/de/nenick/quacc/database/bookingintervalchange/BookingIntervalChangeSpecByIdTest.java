package de.nenick.quacc.database.bookingintervalchange;

import org.junit.Test;

public class BookingIntervalChangeSpecByIdTest extends BookingIntervalChangeTestBase {

    BookingIntervalChangeSpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingIntervalChangeSpecById(id);
        result = bookingIntervalChangeRepository.query(spec);
    }

}