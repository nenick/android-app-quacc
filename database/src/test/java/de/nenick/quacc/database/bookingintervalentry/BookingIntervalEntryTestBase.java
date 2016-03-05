package de.nenick.quacc.database.bookingintervalentry;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountRepository_;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository_;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository_;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategoryRepository_;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryContentValues;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryCursor;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public abstract class BookingIntervalEntryTestBase extends RoboDatabaseTest {

    AccountRepository accountRepository;
    CategoryRepository categoryRepository;
    BookingEntryRepository bookingEntryRepository;
    BookingIntervalRepository bookingIntervalRepository;
    BookingIntervalEntryRepository bookingIntervalEntryRepository;

    BookingIntervalEntryContentValues values;
    long resultId;
    AbstractCursor referencedAccount;
    AbstractCursor referencedCategory;
    AbstractCursor referencedBookingEntry;
    AbstractCursor referencedBookingInterval;
    BookingIntervalEntryCursor result;
    List<BookingIntervalEntryCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = AccountRepository_.getInstance_(context);
        categoryRepository = CategoryRepository_.getInstance_(context);
        bookingEntryRepository = BookingEntryRepository_.getInstance_(context);
        bookingIntervalRepository = BookingIntervalRepository_.getInstance_(context);
        bookingIntervalEntryRepository = BookingIntervalEntryRepository_.getInstance_(context);
    }

    public void givenReferences() {
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategoryCursor.class).get(0);
        referencedBookingEntry = TestDbData.iNeed(BookingEntryContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingEntryRepository, BookingEntryCursor.class).get(0);
        referencedBookingInterval = TestDbData.iNeed(BookingIntervalContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingIntervalRepository, BookingIntervalCursor.class).get(0);

    }

    public void givenEntriesCount(int count) {
        givenReferences();
        created = TestDbData.iNeed(count, BookingIntervalEntryContentValues.class).relatedTo(referencedBookingEntry, referencedBookingInterval).in(bookingIntervalEntryRepository, BookingIntervalEntryCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        Assertions.assertThat(result).hasCount(expected);
    }
}
