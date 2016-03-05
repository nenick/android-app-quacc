package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public class BookingEntrySpecCategoryEntriesByRangeTest extends BookingEntryTestBase {

    BookingEntrySpecCategoryEntriesByRange spec;

    @Test
    public void testSelection() {
        Date currentDate = new Date();
        Date previousDate = new Date(currentDate.getTime() - 10000);

        givenReferences();
        givenEntry(previousDate, "income");
        givenEntry(currentDate, "income");

        whenQuery(previousDate, currentDate, referencedCategory.getId(), "income");
        thenQueryResultCountIs(2);

        whenQuery(currentDate, currentDate, referencedCategory.getId(), "income");
        thenQueryResultCountIs(1);
    }

    private void whenQuery(Date start, Date end, long categoryId, String type) {
        spec = new BookingEntrySpecCategoryEntriesByRange(referencedAccount.getId(), start, end, categoryId, type);
        result = bookingEntryRepository.query(spec);
    }

    private void givenEntry(Date date, String direction) {
        TestDbData.iNeed(BookingEntryContentValues.class)
                .with(BookingEntryColumns.DATE, date)
                .with(BookingEntryColumns.DIRECTION, direction)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingEntryRepository, BookingEntryCursor.class);
    }
}