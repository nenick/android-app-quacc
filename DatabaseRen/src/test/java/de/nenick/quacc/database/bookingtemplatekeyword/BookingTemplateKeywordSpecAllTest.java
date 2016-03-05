package de.nenick.quacc.database.bookingtemplatekeyword;

import org.junit.Test;

public class BookingTemplateKeywordSpecAllTest extends BookingTemplateKeywordTestBase {

    BookingTemplateKeywordSpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingTemplateKeywordSpecAll();
        result = bookingTemplateKeywordRepository.query(spec);
    }
}