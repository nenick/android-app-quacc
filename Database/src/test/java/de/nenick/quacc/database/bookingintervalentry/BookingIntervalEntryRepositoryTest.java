package de.nenick.quacc.database.bookingintervalentry;

import android.database.sqlite.SQLiteException;

import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryContentValues;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntrySelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.testsupport.testdata.GenericQueryAllSpecification;
import de.nenick.quacc.database.testsupport.testdata.TestDbData;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingIntervalEntryRepositoryTest extends BookingIntervalEntryTestBase {

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
    public void testValue_BookingEntryId_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("booking_entry_id may not be NULL"));

        givenMandatoryContentWithoutBookingEntryId();
        whenInsertContent();
    }

    @Test
    public void testValue_BookingEntryId_NotZero() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutBookingEntryId();
        values.putBookingEntryId(0);
        whenInsertContent();
    }

    @Test
    public void testValue_BookingEntryId_NotExist() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("foreign key constraint failed"));

        givenMandatoryContentWithoutBookingEntryId();
        values.putBookingEntryId(987654321);
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
        assertThat(resultId).isNotEqualTo(referencedBookingEntry.getId());
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
        assertThat(result.getBookingEntryAccountId()).isEqualTo(referencedAccount.getId());
        assertThat(result.getBookingEntryCategoryId()).isEqualTo(referencedCategory.getId());
        assertThat(result.getBookingEntryId()).isEqualTo(referencedBookingEntry.getId());
    }

    private void whenQueryAll() {
        result = bookingIntervalEntryRepository.query(new GenericQueryAllSpecification<>(BookingIntervalEntrySelection.class));
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = bookingIntervalEntryRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true, true);
    }

    private void givenMandatoryContentWithoutBookingIntervalId() {
        givenMandatoryContent(false, true);
    }

    private void givenMandatoryContentWithoutBookingEntryId() {
        givenMandatoryContent(true, false);
    }

    private void givenMandatoryContent(boolean withBookingInterval, boolean withBookingEntry) {
        values = new BookingIntervalEntryContentValues();
        referencedAccount = TestDbData.iNeed(AccountContentValues.class).in(accountRepository, AccountSelection.class, AccountCursor.class).get(0);
        referencedCategory = TestDbData.iNeed(CategoryContentValues.class).with(CategoryColumns.LEVEL, 1).in(categoryRepository, CategorySelection.class, CategoryCursor.class).get(0);
        if (withBookingInterval) {
            referencedBookingInterval = TestDbData.iNeed(BookingIntervalContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingIntervalRepository, BookingIntervalSelection.class, BookingIntervalCursor.class).get(0);
            values.putBookingIntervalId(referencedBookingInterval.getId());
        }
        if (withBookingEntry) {
            referencedBookingEntry = TestDbData.iNeed(BookingEntryContentValues.class).relatedTo(referencedAccount, referencedCategory).in(bookingEntryRepository, BookingEntrySelection.class, BookingEntryCursor.class).get(0);
            values.putBookingEntryId(referencedBookingEntry.getId());
        }
    }

}