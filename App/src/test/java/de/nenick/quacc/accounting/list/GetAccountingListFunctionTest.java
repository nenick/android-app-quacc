package de.nenick.quacc.accounting.list;


import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.accounting.list.functions.GetAccountingListFunction;
import de.nenick.quacc.database.AccountingDb;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class GetAccountingListFunctionTest {

    @InjectMocks
    GetAccountingListFunction getAccountingListFunction;

    @Mock
    AccountingDb accountingDb;

    @Mock
    AccountingCursor accountingCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        given(accountingDb.getAll()).willReturn(accountingCursor);
    }

    @Test
    public void shouldDeliverAccountingCursor() {
        assertThat(getAccountingListFunction.apply()).isEqualTo(accountingCursor);
    }
}