package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.account.AccountCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAccountsUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountsUc getAccountsUc;

    @Mock
    AccountCursor accounts;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountsUc.afterInject();
        given(TestApplication.databaseModuleMocks.accountRepository.getAccounts()).willReturn(accounts);

    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accounts.getCount()).willReturn(2);
        given(accounts.getName()).willReturn("TypeA").willReturn("TypeB");

        CharSequence[] apply = getAccountsUc.apply();
        assertThat(apply).hasSize(2);
        verify(accounts, times(2)).moveToNext();
        assertThat(apply[0]).isEqualTo("TypeA");
        assertThat(apply[1]).isEqualTo("TypeB");
    }

}