package de.nenick.quacc.database.bookingintervalchange;

import android.database.sqlite.SQLiteException;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.database.DbConstantValues;
import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeContentValues;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.testdata.GenericQueryAllSpecification;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingIntervalChangeRepositoryTest extends BookingIntervalChangeTestBase {

    @Test
    public void testInsertMinimalContent() {
        givenMandatoryContent();
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_BookingIntervalId_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("booking_interval_id may not be NULL"));

        givenMandatoryContentWithoutBookingIntervalId();
        whenInsertContent();
    }

    @Test
    public void testValue_BookingIntervalId_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutBookingIntervalId();
        values.putBookingIntervalId(0);
        whenInsertContent();
    }

    @Test
    public void testValue_BookingIntervalId_NotExist() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutBookingIntervalId();
        values.putBookingIntervalId(987654321);
        whenInsertContent();
    }

    @Test
    public void testValue_FollowUp_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("follow_up may not be NULL"));

        givenMandatoryContentWithoutFollowUp();
        whenInsertContent();
    }

    @Test
    public void testValue_Date_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("date may not be NULL"));

        givenMandatoryContentWithoutDate();
        whenInsertContent();
    }

    @Test
    public void testValue_Date_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putDate(DbConstantValues.NO_DATE_GIVEN);
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
        assertThat(resultId).isNotEqualTo(referencedBookingInterval.getId());
    }

    private void thenForeignKeysAreCorrect() {
        result.moveToNext();
        assertThat(result.getId()).isNotEqualTo(resultId);
        result.moveToNext();
        assertThat(result.getId()).isEqualTo(resultId);
        assertThat(result.getBookingIntervalAccountId()).isEqualTo(referencedAccount.getId());
        assertThat(result.getBookingIntervalCategoryId()).isEqualTo(referencedCategory.getId());
        assertThat(result.getBookingIntervalId()).isEqualTo(referencedBookingInterval.getId());
    }

    private void whenQueryAll() {
        result = bookingIntervalChangeRepository.query(new GenericQueryAllSpecification<>(BookingIntervalChangeSelection.class));
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = bookingIntervalChangeRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true, true, true);
    }

    private void givenMandatoryContentWithoutBookingIntervalId() {
        givenMandatoryContent(false, true, true);
    }

    private void givenMandatoryContentWithoutDate() {
        givenMandatoryContent(true, false, true);
    }

    private void givenMandatoryContentWithoutFollowUp() {
        givenMandatoryContent(true, true, false);
    }

    private void givenMandatoryContent(boolean withBookingInterval, boolean withDate, boolean withFollowUp) {
        values = new BookingIntervalChangeContentValues();
        if (withBookingInterval) {
            referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
            referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
            referencedBookingInterval = TestDbData.iNeed(BookingIntervalContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingIntervalRepository, BookingIntervalSelection.class, BookingIntervalCursor.class).get(0);
            values.putBookingIntervalId(referencedBookingInterval.getId());
        }
        if (withFollowUp) values.putFollowUp(true);
        if (withDate) values.putDate(new Date());
    }

}