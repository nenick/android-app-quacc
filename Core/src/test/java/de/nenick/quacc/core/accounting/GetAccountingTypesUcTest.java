package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class GetAccountingTypesUcTest extends RobolectricSupportedTest {

    @InjectMocks
    GetAccountingTypesUc getAccountingTypesUc;

    String[] accountingTypes = new String[] {"TypeA", "TypeB"};

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingTypesUc.afterInject();
        given(TestApplication.databaseModuleMocks.accountingTypeRepository.getAccountingTypes()).willReturn(accountingTypes);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] apply = getAccountingTypesUc.apply();
        assertThat(apply).hasSize(2);
        assertThat(apply[0]).isEqualTo("TypeA");
        assertThat(apply[1]).isEqualTo("TypeB");
    }

}