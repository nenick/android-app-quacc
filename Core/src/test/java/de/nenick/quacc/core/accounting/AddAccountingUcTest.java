package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import de.nenick.quacc.core.TestApplication;
import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class AddAccountingUcTest extends RoboCoreTest {

    @InjectMocks
    AddNewAccountingUc_ addNewAccountingUc;

    @Mock
    AccountingCursor accountingCursor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        addNewAccountingUc.afterInject();
    }

    @Test
    public void shouldAddAccounting() {
        given(TestApplication.databaseModuleMocks.accountRepository.getIdFor("Account")).willReturn(42l);
        given(TestApplication.databaseModuleMocks.accountingCategoryRepository.getIdFor("AccountingCategory")).willReturn(22l);
        Date date = new Date();
        addNewAccountingUc.apply("Account", "Übertrag", "Monatlich", "AccountingCategory", date, 1212, "money money");
        verify(TestApplication.databaseModuleMocks.accountingRepository).insertAccounting(42, AccountingType.Übertrag, AccountingInterval.Monatlich, 22, date, 1212, "money money");
    }

}