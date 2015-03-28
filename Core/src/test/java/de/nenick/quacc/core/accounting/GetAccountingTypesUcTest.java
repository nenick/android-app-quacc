package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAccountingTypesUcTest extends RobolectricSupportedTest {

    @InjectMocks
    GetAccountingTypesUc getAccountingTypesUc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingTypesUc.afterInject();
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        CharSequence[] apply = getAccountingTypesUc.apply();
        assertThat(apply).isNotEmpty();
    }

}