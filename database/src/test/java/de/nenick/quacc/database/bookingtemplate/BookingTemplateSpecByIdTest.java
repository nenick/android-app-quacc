package de.nenick.quacc.database.bookingtemplate;

import org.junit.Test;

public class BookingTemplateSpecByIdTest extends BookingTemplateTestBase {

    BookingTemplateSpecById spec;

    @Test
    public void testSelection() {
        givenEntriesCount(2);
        long id = created.get(0).getId();
        whenQuery(id);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(long id) {
        spec = new BookingTemplateSpecById(id);
        result = bookingTemplateRepository.query(spec);
    }

}