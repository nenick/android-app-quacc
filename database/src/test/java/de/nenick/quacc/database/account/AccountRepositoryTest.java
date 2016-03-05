package de.nenick.quacc.database.account;

import android.database.sqlite.SQLiteException;

import org.junit.Test;

import de.nenick.quacc.database.provider.account.AccountContentValues;

import static de.nenick.quacc.database.testsupport.CauseMatcher.containsMessage;
import static org.assertj.core.api.Assertions.assertThat;

public class AccountRepositoryTest extends AccountTestBase {

    @Test
    public void testInsertMinimalContent() {
        givenMandatoryContent();
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_InitialValue_NotNull() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("initialvalue may not be NULL"));

        givenMandatoryContentWithoutInitialValue();
        whenInsertContent();
    }

    @Test
    public void testValue_InitialValue_AllowZero() {
        givenMandatoryContentWithoutInitialValue();
        values.putInitialvalue(0);
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_InitialValue_AllowNegative() {
        givenMandatoryContentWithoutInitialValue();
        values.putInitialvalue(-100);
        whenInsertContent();
        thenEntryIsInserted();
    }

    @Test
    public void testValue_Name_NotEmpty() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("constraint failed"));

        givenMandatoryContent();
        values.putName("");
        whenInsertContent();
    }

    @Test
    public void testValue_Name_NotNull() {
        expectedException.expect(IllegalArgumentException.class);
        expectedException.expectMessage("name must not be null");

        givenMandatoryContent();
        //noinspection ConstantConditions
        values.putName(null);
        whenInsertContent();
    }

    @Test
    public void testValue_Name_NotDuplicate() {
        expectedException.expect(SQLiteException.class);
        expectedException.expectCause(containsMessage("name is not unique"));

        givenMandatoryContent();
        values.putName("Same name");
        whenInsertContent();

        givenMandatoryContent();
        values.putName("Same name");
        whenInsertContent();
    }

    private void thenEntryIsInserted() {
        assertThat(resultId).isPositive();
    }

    private void whenInsertContent() {
        resultId = accountRepository.insert(values);
    }

    private void givenMandatoryContent() {
        givenMandatoryContent(true);
    }

    private void givenMandatoryContent(boolean withInitialValue) {
        values = new AccountContentValues();
        values.putName("Account Name");
        if (withInitialValue) values.putInitialvalue(100);
    }

    private void givenMandatoryContentWithoutInitialValue() {
        givenMandatoryContent(false);
    }
}