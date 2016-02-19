package de.nenick.quacc.database.bookinginterval;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public class BookingIntervalSpecByRangeTest extends BookingIntervalTestBase {

    BookingIntervalSpecByRange spec;

    @Test
    public void testSelection() {

        long current = System.currentTimeMillis();
        Date currentDate = new Date(current);
        Date previousDate = new Date(current - 10000);

        givenReferences();
        givenEntry(previousDate, previousDate);
        givenEntry(currentDate, currentDate);

        whenQuery(previousDate, currentDate);
        thenQueryResultCountIs(2);

        whenQuery(currentDate, currentDate);
        thenQueryResultCountIs(1);
    }

    private void whenQuery(Date start, Date end) {
        spec = new BookingIntervalSpecByRange(referencedAccount.getId(), start, end);
        result = bookingIntervalRepository.query(spec);
    }

    private void givenEntry(Date startDate, Date endDate) {
        TestDbData.iNeed(BookingIntervalContentValues.class)
                .with(BookingIntervalColumns.DATE_START, startDate)
                .with(BookingIntervalColumns.DATE_END, endDate)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingIntervalRepository, BookingIntervalSelection.class, BookingIntervalCursor.class);
    }

}