package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountingIntervalsFunctionTest {

    @InjectMocks
    GetAccountingIntervalsFunction getAccountingIntervalsUc;

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