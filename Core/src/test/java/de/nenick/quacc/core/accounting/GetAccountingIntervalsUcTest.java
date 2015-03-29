package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class GetAccountingIntervalsUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingIntervalsUc_ getAccountingIntervalsUc;

    String[] accountingIntervals = new String[]{"TypeA", "TypeB"};

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingIntervalsUc.afterInject();
        given(TestApplication.databaseModuleMocks.accountingIntervalRepository.getAccountingIntervals()).willReturn(accountingIntervals);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] apply = getAccountingIntervalsUc.apply();
        assertThat(apply).hasSize(2);
        assertThat(apply[0]).isEqualTo("TypeA");
        assertThat(apply[1]).isEqualTo("TypeB");
    }

}