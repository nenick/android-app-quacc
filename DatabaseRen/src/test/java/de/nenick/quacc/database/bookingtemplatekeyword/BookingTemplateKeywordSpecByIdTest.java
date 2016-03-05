package de.nenick.quacc.database.bookingtemplatekeyword;

import org.junit.Test;

public class BookingTemplateKeywordSpecByIdTest extends BookingTemplateKeywordTestBase {

    BookingTemplateKeywordSpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingTemplateKeywordSpecById(id);
        result = bookingTemplateKeywordRepository.query(spec);
    }
}