package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAccountingListUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingListUc_ getAccountingListUc;

    @Mock
    AccountingCursor accountingCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingListUc.afterInject();
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(TestApplication.databaseModuleMocks.accountingRepository.getAccountings()).willReturn(accountingCursor);
        assertThat(getAccountingListUc.apply()).isEqualTo(accountingCursor) ;
    }

}