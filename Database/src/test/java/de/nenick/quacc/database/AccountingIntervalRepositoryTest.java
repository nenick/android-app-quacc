package de.nenick.quacc.database;

import org.junit.Test;

import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountingIntervalRepositoryTest extends RoboDatabaseTest {

    AccountingIntervalRepository repository = new AccountingIntervalRepository();

    @Test
    public void testGetAccountingTypes() throws Exception {
        CharSequence[] accountingTypes = repository.getAccountingIntervals();
        assertThat(accountingTypes).hasSize(4);
        assertThat(accountingTypes).contains("Einmahlig");
        assertThat(accountingTypes).contains("Wöchentlich");
        assertThat(accountingTypes).contains("Monatlich");
        assertThat(accountingTypes).contains("Alle_3_Monate");
    }
}