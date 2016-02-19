package de.nenick.quacc.database.bookingentry;

import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public class BookingEntrySpecByIntervalTest extends BookingEntryTestBase {

    BookingEntrySpecByInterval spec;

    @Test
    public void testSelection() {
        givenEntry("firstInterval");
        givenEntry("secondInterval");
        whenQuery("firstInterval");
        thenQueryResultCountIs(1);
    }

    private void whenQuery(String interval) {
        spec = new BookingEntrySpecByInterval(interval);
        result = bookingEntryRepository.query(spec);
    }

    private void givenEntry(String interval) {
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategoryCursor.class).get(0);
        TestDbData.iNeed(BookingEntryContentValues.class)
                .with(BookingEntryColumns.INTERVAL, interval)
                .relatedTo(referencedAccount, referencedCategory)
                .in(bookingEntryRepository, BookingEntryCursor.class);
    }
}