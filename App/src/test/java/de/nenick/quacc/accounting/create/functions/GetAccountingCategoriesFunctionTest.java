package de.nenick.quacc.accounting.create.functions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

public class GetAccountingCategoriesFunctionTest {

    @InjectMocks
    GetAccountingCategoriesFunction getAccountingCategoriesFunction;

    @Mock
    CategoryDb categoryDb;

    @Mock
    CategoryCursor accountingCategoryCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        given(categoryDb.getAllFor(any(String[].class), any(String[].class), anyString())).willReturn(accountingCategoryCursor);
    }

    @Test
    public void shouldDeliverAccountingTypes() {
        given(accountingCategoryCursor.getCount()).willReturn(2);
        given(accountingCategoryCursor.getName()).willReturn("One").willReturn("Two");
        CharSequence[] apply = getAccountingCategoriesFunction.apply("foo", "bar");
        assertThat(apply.length).isEqualTo(2);
        assertThat(apply).contains("One");
        assertThat(apply).contains("Two");
    }

    @Test
    public void shouldCloseCursor() {
        getAccountingCategoriesFunction.apply("foo", "bar");
        verify(accountingCategoryCursor).close();
    }
}