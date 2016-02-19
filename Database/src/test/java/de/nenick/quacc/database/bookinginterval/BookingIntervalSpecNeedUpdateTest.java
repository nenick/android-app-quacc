package de.nenick.quacc.database.bookinginterval;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public class BookingIntervalSpecNeedUpdateTest extends BookingIntervalTestBase {

    BookingIntervalSpecNeedUpdate spec;

    @Test
    public void testSelection() {
        long current = System.currentTimeMillis();
        Date currentDate = new Date(current);
        Date previousDate = new Date(current - 10000);
        Date futureDate = new Date(current + 10000);

        givenReferences();
        givenEntry(previousDate);
        givenEntry(currentDate);

        whenQuery(currentDate);
        thenQueryResultCountIs(1);

        whenQuery(futureDate);
        thenQueryResultCountIs(2);
    }

    private void whenQuery(Date date) {
        spec = new BookingIntervalSpecNeedUpdate(referencedAccount.getId(), date);
        result = bookingIntervalRepository.query(spec);
    }

    private void givenEntry(Date date) {
        TestDbData.iNeed(BookingIntervalContentValues.class)
                .with(BookingIntervalColumns.DATE_UPDATED_UNTIL, date)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingIntervalRepository, BookingIntervalSelection.class, BookingIntervalCursor.class);
    }
}