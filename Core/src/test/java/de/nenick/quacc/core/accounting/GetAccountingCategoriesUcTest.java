package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GetAccountingCategoriesUcTest extends RoboCoreTest {

    @InjectMocks
    GetAccountingCategoriesUc_ getAccountingCategoriesUc;

    @Mock
    AccountingCategoryCursor accountingCategories;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        getAccountingCategoriesUc.afterInject();
        given(TestApplication.databaseModuleMocks.accountingCategoryRepository.getAccountingCategories()).willReturn(accountingCategories);

    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accountingCategories.getCount()).willReturn(2);
        given(accountingCategories.getName()).willReturn("TypeA").willReturn("TypeB");

        CharSequence[] apply = getAccountingCategoriesUc.apply();
        assertThat(apply).hasSize(2);
        verify(accountingCategories, times(2)).moveToNext();
        assertThat(apply[0]).isEqualTo("TypeA");
        assertThat(apply[1]).isEqualTo("TypeB");
    }

}