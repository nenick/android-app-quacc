package de.nenick.quacc.database;

import org.junit.Test;

import de.nenick.quacc.database.robolectric.RoboDatabaseTest;

import static org.assertj.core.api.Assertions.assertThat;


public class AccountingTypeRepositoryTest extends RoboDatabaseTest {

    AccountingTypeRepository repository = new AccountingTypeRepository();

    @Test
    public void testGetAccountingTypes() throws Exception {
        CharSequence[] accountingTypes = repository.getAccountingTypes();
        assertThat(accountingTypes).hasSize(3);
        assertThat(accountingTypes).contains("Einnahme");
        assertThat(accountingTypes).contains("Ausgabe");
        assertThat(accountingTypes).contains("Übertrag");
    }
}