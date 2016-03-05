package de.nenick.quacc.database.bookingtemplate;

import org.assertj.android.api.Assertions;
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
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public abstract class BookingTemplateTestBase extends RoboDatabaseTest {

    AccountRepository accountRepository;
    CategoryRepository categoryRepository;
    BookingTemplateRepository bookingTemplateRepository;

    BookingTemplateContentValues values;
    long resultId;
    AbstractCursor referencedAccount;
    AbstractCursor referencedCategory;
    BookingTemplateCursor result;
    List<BookingTemplateCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = AccountRepository_.getInstance_(context);
        categoryRepository = CategoryRepository_.getInstance_(context);
        bookingTemplateRepository = BookingTemplateRepository_.getInstance_(context);
    }

    public void givenReferences() {
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategoryCursor.class).get(0);
    }

    public void givenEntriesCount(int count) {
        givenReferences();
        created = TestDbData.iNeed(count, BookingTemplateContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingTemplateRepository, BookingTemplateCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        Assertions.assertThat(result).hasCount(expected);
    }
}
