package de.nenick.quacc.database.bookinginterval;

import android.database.sqlite.SQLiteException;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.DbConstantValues;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.testdata.GenericQueryAllSpecification;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingIntervalRepositoryTest extends BookingIntervalTestBase {

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
    public void testValue_AccountId_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutAccountId();
        values.putAccountId(0);
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
    public void testValue_CategoryId_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutCategoryId();
        values.putCategoryId(0);
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
    public void testValue_DateStart_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("date_start may not be NULL"));

        givenMandatoryContentWithoutDateStart();
        whenInsertContent();
    }

    @Test
    public void testValue_DateStart_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContentWithoutDateStart();
        values.putDateStart(DbConstantValues.NO_DATE_GIVEN);
        whenInsertContent();
    }

    @Test
    public void testValue_DateEnd_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("date_end may not be NULL"));

        givenMandatoryContentWithoutDateEnd();
        whenInsertContent();
    }

    @Test
    public void testValue_DateEnd_AllowZero() {
        givenMandatoryContentWithoutDateEnd();
        values.putDateEnd(DbConstantValues.NO_DATE_GIVEN);
        whenInsertContent();
    }

    @Test
    public void testValue_DateLast_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("date_last may not be NULL"));

        givenMandatoryContentWithoutDateLast();
        whenInsertContent();
    }

    @Test
    public void testValue_DateLast_AllowZero() {
        givenMandatoryContentWithoutDateLast();
        values.putDateLast(DbConstantValues.NO_DATE_GIVEN);
        whenInsertContent();
    }

    @Test
    public void testValue_DateUpdatedUntil_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("date_updated_until may not be NULL"));

        givenMandatoryContentWithoutDateUpdatedUntil();
        whenInsertContent();
    }

    @Test
    public void testValue_DateUpdatedUntil_AllowZero() {
        givenMandatoryContentWithoutDateUpdatedUntil();
        values.putDateUpdatedUntil(DbConstantValues.NO_DATE_GIVEN);
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
    public void testValue_Amount_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("amount may not be NULL"));

        givenMandatoryContentWithoutValue();
        whenInsertContent();
    }

    @Test
    public void testValue_Amount_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContentWithoutValue();
        values.putAmount(0);
        whenInsertContent();
    }

    @Test
    public void testValue_Amount_NotNegative() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContentWithoutValue();
        values.putAmount(-100);
        whenInsertContent();
    }

    @Test
    public void testForeignKeys() {
        whenStoreTwoEntries();
        whenQueryAll();
        thenForeignKeysAreCorrect();
    }

    private void whenStoreTwoEntries() {
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
        result = bookingIntervalRepository.query(new GenericQueryAllSpecification<>(BookingIntervalSelection.class));
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = bookingIntervalRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true, true, true, true, true, true, true);
    }

    private void givenMandatoryContentWithoutAccountId() {
        givenMandatoryContent(false, true, true, true, true, true, true);
    }

    private void givenMandatoryContentWithoutCategoryId() {
        givenMandatoryContent(true, false, true, true, true, true, true);
    }

    private void givenMandatoryContentWithoutDateStart() {
        givenMandatoryContent(true, true, false, true, true, true, true);
    }

    private void givenMandatoryContentWithoutDateEnd() {
        givenMandatoryContent(true, true, true, false, true, true, true);
    }

    private void givenMandatoryContentWithoutDateLast() {
        givenMandatoryContent(true, true, true, true, false, true, true);
    }

    private void givenMandatoryContentWithoutDateUpdatedUntil() {
        givenMandatoryContent(true, true, true, true, true, false, true);
    }

    private void givenMandatoryContentWithoutValue() {
        givenMandatoryContent(true, true, true, true, true, true, false);
    }

    private void givenMandatoryContent(boolean withAccountId, boolean withCategoryId, boolean withDateStart, boolean withDateEnd, boolean withDateLast, boolean withDateUpdatedUntil, boolean withValue) {
        values = new BookingIntervalContentValues();
        values.putComment(null);
        if (withAccountId) {
            referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
            values.putAccountId(referencedAccount.getId());
        }
        if (withCategoryId) {
            referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
            values.putCategoryId(referencedCategory.getId());
        }
        values.putInterval("this interval");
        if (withDateStart) values.putDateStart(new Date());
        if (withDateEnd) values.putDateEnd(new Date());
        if (withDateLast) values.putDateLast(new Date());
        if (withDateUpdatedUntil) values.putDateUpdatedUntil(new Date());
        values.putDirection("some direction");
        if (withValue) values.putAmount(100);
    }
}