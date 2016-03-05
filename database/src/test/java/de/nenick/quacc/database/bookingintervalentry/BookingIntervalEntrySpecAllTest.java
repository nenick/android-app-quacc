package de.nenick.quacc.database.bookingintervalentry;

import org.junit.Test;

public class BookingIntervalEntrySpecAllTest extends BookingIntervalEntryTestBase {

    BookingIntervalEntrySpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingIntervalEntrySpecAll();
        result = bookingIntervalEntryRepository.query(spec);
    }

}