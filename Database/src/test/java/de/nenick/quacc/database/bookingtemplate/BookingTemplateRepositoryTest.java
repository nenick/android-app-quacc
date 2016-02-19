package de.nenick.quacc.database.bookingtemplate;


import android.database.sqlite.SQLiteException;

import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.testdata.GenericQueryAllSpecification;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingTemplateRepositoryTest extends BookingTemplateTestBase {

    @Test
    public void testInsertMinimalContent() {
        givenMandatoryContent();
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_AccountId_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("account_id may not be NULL"));

        givenMandatoryContentWithoutAccountId();
        whenInsertContent();
    }

    @Test
    public void testValue_AccountId_NotExist() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutAccountId();
        values.putAccountId(987654321);
        whenInsertContent();
    }

    @Test
    public void testValue_CategoryId_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("category_id may not be NULL"));

        givenMandatoryContentWithoutCategoryId();
        whenInsertContent();
    }

    @Test
    public void testValue_CategoryId_NotExist() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutCategoryId();
        values.putCategoryId(987654321);
        whenInsertContent();
    }

    @Test
    public void testValue_Interval_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("interval must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putInterval(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Interval_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putInterval("");
        whenInsertContent();
    }

    @Test
    public void testValue_Direction_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("direction must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putDirection(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Direction_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putDirection("");
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
        whenInsertContent();
        assertThat(resultId).isNotEqualTo(referencedAccount.getId());
        assertThat(resultId).isNotEqualTo(referencedCategory.getId());
    }

    private void thenForeignKeysAreCorrect() {
        result.moveToNext();
        assertThat(result.getId()).isNotEqualTo(resultId);
        result.moveToNext();
        assertThat(result.getId()).isEqualTo(resultId);
        assertThat(result.getAccountId()).isEqualTo(referencedAccount.getId());
        assertThat(result.getCategoryId()).isEqualTo(referencedCategory.getId());
    }

    private void whenQueryAll() {
        result = bookingTemplateRepository.query(new GenericQueryAllSpecification<>(BookingTemplateSelection.class));
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = bookingTemplateRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true, true);
    }

    private void givenMandatoryContentWithoutAccountId() {
        givenMandatoryContent(false, true);
    }

    private void givenMandatoryContentWithoutCategoryId() {
        givenMandatoryContent(true, false);
    }

    private void givenMandatoryContent(boolean withAccountId, boolean withCategoryId) {
        values = new BookingTemplateContentValues();
        if (withAccountId) {
            referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
            values.putAccountId(referencedAccount.getId());
        }
        if (withCategoryId) {
            referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
            values.putCategoryId(referencedCategory.getId());
        }
        values.putInterval("this interval");
        values.putDirection("my direction");
    }
}