package de.nenick.quacc.database.bookingtemplatekeyword;

import org.junit.Test;

public class BookingTemplateKeywordSpecByTemplateIdTest extends BookingTemplateKeywordTestBase {

    BookingTemplateKeywordSpecByTemplateId spec;

    @Test
    public void testSelection() {
        givenEntriesCount(1);
        givenEntriesCount(1);
        long id = created.get(0).getBookingTemplateId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingTemplateKeywordSpecByTemplateId(id);
        result = bookingTemplateKeywordRepository.query(spec);
    }
}