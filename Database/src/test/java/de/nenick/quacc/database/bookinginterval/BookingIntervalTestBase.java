package de.nenick.quacc.database.bookinginterval;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountRepository_;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategoryRepository_;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static org.assertj.android.api.Assertions.assertThat;

public abstract class BookingIntervalTestBase extends RoboDatabaseTest {

    AccountRepository accountRepository;
    CategoryRepository categoryRepository;
    BookingIntervalRepository bookingIntervalRepository;

    BookingIntervalContentValues values;
    long resultId;
    AbstractCursor referencedAccount;
    AbstractCursor referencedCategory;
    BookingIntervalCursor result;
    List<BookingIntervalCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = AccountRepository_.getInstance_(context);
        categoryRepository = CategoryRepository_.getInstance_(context);
        bookingIntervalRepository = BookingIntervalRepository_.getInstance_(context);
    }

    public void givenReferences() {
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
    }

    public void givenEntriesCount(int count) {
        givenReferences();
        created = TestDbData.iNeed(count, BookingIntervalContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingIntervalRepository, BookingIntervalSelection.class, BookingIntervalCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        assertThat(result).hasCount(expected);
    }
}
