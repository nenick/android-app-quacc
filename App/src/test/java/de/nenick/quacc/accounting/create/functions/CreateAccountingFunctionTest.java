package de.nenick.quacc.accounting.create.functions;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import de.nenick.quacc.accounting.creation.CreateAccountingFunction;
import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.category.CategoryDb;
import de.nenick.quacc.database.accounting.AccountingDb;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class CreateAccountingFunctionTest {

    public static final String ACCOUNTING_TYPE = "Übertrag";
    public static final String ACCOUNTING_INTERVAL = "Monatlich";
    Date DATE = new Date();
    public static final String ACCOUNT = "Bar";
    public static final String CATEGORY_NAME = "Miete";
    public static final long CATEGORY_ID = 2l;
    public static final long ACCOUNT_ID = 1l;
    public static final String COMMENT = "money money";
    public static final int VALUE = 1212;
    @InjectMocks
    CreateAccountingFunction function;

    @Mock
    AccountDb accountDb;

    @Mock
    CategoryDb categoryDb;

    @Mock
    AccountingDb accountingDb;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddAccounting() {
        given(accountDb.getIdByName(ACCOUNT)).willReturn(ACCOUNT_ID);
        given(categoryDb.getIdByName(CATEGORY_NAME)).willReturn(CATEGORY_ID);
        function.apply(ACCOUNT, ACCOUNTING_TYPE, ACCOUNTING_INTERVAL, CATEGORY_NAME, DATE, VALUE, COMMENT);
        verify(accountingDb).insert(ACCOUNT_ID, ACCOUNTING_TYPE, ACCOUNTING_INTERVAL, CATEGORY_ID, DATE, COMMENT, 1212);
    }

}