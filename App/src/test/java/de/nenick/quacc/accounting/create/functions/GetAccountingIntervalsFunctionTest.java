package de.nenick.quacc.accounting.create.functions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Collection;

import de.nenick.quacc.accounting.GetAccountingIntervalsFunction;

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
        Collection<String> apply = getAccountingIntervalsUc.apply();
        assertThat(apply.size()).isPositive();
    }

}