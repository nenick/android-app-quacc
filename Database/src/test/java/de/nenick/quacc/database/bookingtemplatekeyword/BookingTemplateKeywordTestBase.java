package de.nenick.quacc.database.bookingtemplatekeyword;

import org.assertj.android.api.Assertions;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.List;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountRepository_;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository_;
import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategoryRepository_;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordContentValues;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.RoboDatabaseTest;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

public abstract class BookingTemplateKeywordTestBase extends RoboDatabaseTest {

    AccountRepository accountRepository;
    CategoryRepository categoryRepository;
    BookingTemplateRepository bookingTemplateRepository;
    BookingTemplateKeywordRepository bookingTemplateKeywordRepository;

    BookingTemplateKeywordContentValues values;
    long resultId;
    AbstractCursor referencedAccount;
    AbstractCursor referencedCategory;
    AbstractCursor referencedBookingTemplate;
    BookingTemplateKeywordCursor result;
    List<BookingTemplateKeywordCursor> created;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        accountRepository = AccountRepository_.getInstance_(context);
        categoryRepository = CategoryRepository_.getInstance_(context);
        bookingTemplateRepository = BookingTemplateRepository_.getInstance_(context);
        bookingTemplateKeywordRepository = BookingTemplateKeywordRepository_.getInstance_(context);
    }

    public void givenReferences() {
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
        referencedBookingTemplate = TestDbData.iNeed(BookingTemplateContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingTemplateRepository, BookingTemplateSelection.class, BookingTemplateCursor.class).get(0);
    }

    public void givenEntriesCount(int count) {
        givenReferences();
        created = TestDbData.iNeed(count, BookingTemplateKeywordContentValues.class).relatedTo(referencedBookingTemplate).in(bookingTemplateKeywordRepository, BookingTemplateKeywordSelection.class, BookingTemplateKeywordCursor.class);
    }

    public void thenQueryResultCountIs(int expected) {
        Assertions.assertThat(result).hasCount(expected);
    }
}
