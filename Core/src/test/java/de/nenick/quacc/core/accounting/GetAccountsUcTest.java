package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.robolectric.RoboCoreTest;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountsUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountsUc_ getAccountsUc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] apply = getAccountsUc.apply();
        assertThat(apply.length).isPositive();
    }

}