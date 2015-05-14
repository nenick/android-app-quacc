package de.nenick.quacc.accounting.create;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GetAccountsFunctionTest {

    @InjectMocks
    GetAccountsFunction getAccountsUc;

    @Mock
    AccountDb accountDb;

    @Mock
    AccountCursor accountCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        given(accountDb.getAll()).willReturn(accountCursor);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accountDb.getAll()).willReturn(accountCursor);
        given(accountCursor.getCount()).willReturn(2);
        given(accountCursor.getName()).willReturn("One").willReturn("Two");
        CharSequence[] apply = getAccountsUc.apply();
        assertThat(apply.length).isEqualTo(2);
        assertThat(apply).contains("One");
        assertThat(apply).contains("Two");
    }

    @Test
    public void shouldCloseCursor() {
        getAccountsUc.apply();
        verify(accountCursor).close();
    }
}