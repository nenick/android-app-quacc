package de.nenick.quacc.database;

import org.junit.Test;

import de.nenick.quacc.database.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountingTypeRepositoryTest extends RobolectricSupportedTest {

    AccountingTypeRepository repository = new AccountingTypeRepository();

    @Test
    public void testGetAccountingTypes() throws Exception {
        String[] accountingTypes = repository.getAccountingTypes();
        assertThat(accountingTypes).hasSize(3);
        assertThat(accountingTypes).contains("Einnahme");
        assertThat(accountingTypes).contains("Ausgabe");
        assertThat(accountingTypes).contains("Übertrag");
    }
}