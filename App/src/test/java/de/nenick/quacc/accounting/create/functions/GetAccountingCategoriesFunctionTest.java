package de.nenick.quacc.accounting.create.functions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.accounting.create.functions.GetAccountingCategoriesFunction;
import de.nenick.quacc.database.AccountingCategoryDb;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class GetAccountingCategoriesFunctionTest {

    @InjectMocks
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    @Mock
    AccountingCategoryDb accountingCategoryDb;

    @Mock
    AccountingCategoryCursor accountingCategoryCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        given(accountingCategoryDb.getAll()).willReturn(accountingCategoryCursor);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accountingCategoryCursor.getCount()).willReturn(2);
        given(accountingCategoryCursor.getName()).willReturn("One").willReturn("Two");
        CharSequence[] apply = getAccountingCategoriesFunction.apply();
        assertThat(apply.length).isEqualTo(2);
        assertThat(apply).contains("One");
        assertThat(apply).contains("Two");
    }

    @Test
    public void shouldCloseCursor() {
        getAccountingCategoriesFunction.apply();
        verify(accountingCategoryCursor).close();
    }
}