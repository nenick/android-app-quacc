package de.nenick.quacc.database.bookingintervalentry;

import org.junit.Test;

public class BookingIntervalEntrySpecByIdTest extends BookingIntervalEntryTestBase {

    BookingIntervalEntrySpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingIntervalEntrySpecById(id);
        result = bookingIntervalEntryRepository.query(spec);
    }

}