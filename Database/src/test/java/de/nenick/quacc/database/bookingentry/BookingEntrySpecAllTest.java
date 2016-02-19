package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

public class BookingEntrySpecAllTest extends BookingEntryTestBase {

    BookingEntrySpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingEntrySpecAll();
        result = bookingEntryRepository.query(spec);
    }
}