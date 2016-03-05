package de.nenick.quacc.database.bookingtemplate;

import org.junit.Test;

public class BookingTemplateSpecAllTest extends BookingTemplateTestBase {

    BookingTemplateSpecAll spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        whenQuery();
        thenQueryResultCountIs(2);
    }

    private void whenQuery() {
        spec = new BookingTemplateSpecAll();
        result = bookingTemplateRepository.query(spec);
    }

}