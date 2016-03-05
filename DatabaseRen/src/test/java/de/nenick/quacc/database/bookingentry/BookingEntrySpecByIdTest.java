package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

public class BookingEntrySpecByIdTest extends BookingEntryTestBase {

    BookingEntrySpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingEntrySpecById(id);
        result = bookingEntryRepository.query(spec);
    }
}