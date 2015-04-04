package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.robolectric.RoboCoreTest;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountingTypesUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingTypesUc_ getAccountingTypesUc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] accountingTypes = getAccountingTypesUc.apply();
        assertThat(accountingTypes).hasSize(3);
        assertThat(accountingTypes).contains("Einnahme");
        assertThat(accountingTypes).contains("Ausgabe");
        assertThat(accountingTypes).contains("Übertrag");
    }

}