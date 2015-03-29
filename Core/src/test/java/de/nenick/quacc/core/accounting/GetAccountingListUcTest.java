package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAccountingListUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingListUc getAccountingListUc;

    @Mock
    AccountingCursor accountingCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingListUc.afterInject();
        given(TestApplication.databaseModuleMocks.accountingRepository.getAccountings()).willReturn(accountingCursor);

    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accountingCursor.getCount()).willReturn(2);
        given(accountingCursor.getName()).willReturn("TypeA").willReturn("TypeB");

        CharSequence[] apply = getAccountingListUc.apply();
        assertThat(apply).hasSize(2);
        verify(accountingCursor, times(2)).moveToNext();
        assertThat(apply[0]).isEqualTo("TypeA");
        assertThat(apply[1]).isEqualTo("TypeB");
    }

}