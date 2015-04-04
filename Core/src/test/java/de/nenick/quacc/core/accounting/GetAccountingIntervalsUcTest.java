package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.robolectric.RoboCoreTest;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountingIntervalsUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingIntervalsUc_ getAccountingIntervalsUc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] apply = getAccountingIntervalsUc.apply();
        assertThat(apply.length).isPositive();
    }

}