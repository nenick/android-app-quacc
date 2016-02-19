package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static org.assertj.core.api.Assertions.assertThat;

public class BookingEntrySpecCategorySummeryByRangeTest extends BookingEntryTestBase {

    BookingEntrySpecCategorySummeryByRange spec;

    @Test
    public void testSelection() {
        Date currentDate = new Date();
        Date previousDate = new Date(currentDate.getTime() - 10000);

        givenReferences();
        givenEntry(previousDate);
        givenEntry(currentDate);

        whenQuery(previousDate, currentDate);
        thenQueryResultCountIs(2);

        whenQuery(currentDate, currentDate);
        thenQueryResultCountIs(1);

        thenContainsMinDate();
    }

    private void thenContainsMinDate() {
        assertThat(result.getLong(result.getColumnIndex("minDate"))).isPositive();
    }

    private void whenQuery(Date start, Date end) {
        spec = new BookingEntrySpecCategorySummeryByRange(referencedAccount.getId(), start, end);
        result = bookingEntryRepository.query(spec);
        result.moveToNext();
    }

    private void givenEntry(Date date) {
        TestDbData.iNeed(BookingEntryContentValues.class)
                .with(BookingEntryColumns.DATE, date)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingEntryRepository, BookingEntrySelection.class, BookingEntryCursor.class);
    }
}