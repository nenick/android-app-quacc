package de.nenick.quacc.database.bookingtemplatekeyword;

import android.database.sqlite.SQLiteException;

import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordContentValues;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingTemplateKeywordRepositoryTest extends BookingTemplateKeywordTestBase {

    @Test
    public void testInsertMinimalContent() {
        givenMandatoryContent();
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_Name_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putText("");
        whenInsertContent();
    }

    @Test
    public void testValue_Name_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("text must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putText(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Name_NotDuplicate() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("text is not unique"));

        givenMandatoryContent();
        values.putText("Same text");
        whenInsertContent();

        givenMandatoryContent();
        values.putText("Same text");
        whenInsertContent();
    }

    @Test
    public void testValue_BookingTemplateId_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("booking_template_id may not be NULL"));

        givenMandatoryContentWithoutBookingTemplateId();
        whenInsertContent();
    }

    @Test
    public void testValue_BookingTemplateId_NotExist() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutBookingTemplateId();
        values.putBookingTemplateId(987654321);
        whenInsertContent();
    }

    @Test
    public void testForeignKeys() {
        whenStoreTwoEntriesWithReferences();
        whenQueryAll();
        thenForeignKeysAreCorrect();
    }

    private void whenStoreTwoEntriesWithReferences() {
        givenMandatoryContent();
        whenInsertContent();
        values.putText("another text");
        whenInsertContent();
        assertThat(resultId).isNotEqualTo(referencedAccount.getId());
        assertThat(resultId).isNotEqualTo(referencedCategory.getId());
    }

    private void thenForeignKeysAreCorrect() {
        result.moveToNext();
        assertThat(result.getId()).isNotEqualTo(resultId);
        result.moveToNext();
        assertThat(result.getId()).isEqualTo(resultId);
        assertThat(result.getBookingTemplateAccountId()).isEqualTo(referencedAccount.getId());
        assertThat(result.getBookingTemplateCategoryId()).isEqualTo(referencedCategory.getId());
        assertThat(result.getBookingTemplateId()).isEqualTo(referencedBookingTemplate.getId());
    }

    private void whenQueryAll() {
        result = bookingTemplateKeywordRepository.query(new BookingTemplateKeywordSpecAll());
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = bookingTemplateKeywordRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true);
    }

    private void givenMandatoryContentWithoutBookingTemplateId() {
        givenMandatoryContent(false);
    }

    private void givenMandatoryContent(boolean withBookingTemplateId) {
        values = new BookingTemplateKeywordContentValues();
        values.putText("some text");
        if (withBookingTemplateId) {
            referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountCursor.class).get(0);
            referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategoryCursor.class).get(0);
            referencedBookingTemplate = TestDbData.iNeed(BookingTemplateContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingTemplateRepository, BookingTemplateCursor.class).get(0);
            values.putBookingTemplateId(referencedBookingTemplate.getId());
        }
    }

}