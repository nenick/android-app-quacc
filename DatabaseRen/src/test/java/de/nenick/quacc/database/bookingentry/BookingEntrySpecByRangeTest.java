package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public class BookingEntrySpecByRangeTest extends BookingEntryTestBase {

    BookingEntrySpecByRange spec;

    @Test
    public void testSelection() {

        long current = System.currentTimeMillis();
        Date currentDate = new Date(current);
        Date previousDate = new Date(current - 10000);

        givenReferences();
        givenEntry(previousDate);
        givenEntry(currentDate);

        whenQuery(previousDate, currentDate);
        thenQueryResultCountIs(2);

        whenQuery(currentDate, currentDate);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(Date start, Date end) {
        spec = new BookingEntrySpecByRange(referencedAccount.getId(), start, end);
        result = bookingEntryRepository.query(spec);
    }

    private void givenEntry(Date date) {
        TestDbData.iNeed(BookingEntryContentValues.class)
                .with(BookingEntryColumns.DATE, date)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingEntryRepository, BookingEntryCursor.class);
    }
}