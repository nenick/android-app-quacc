package de.nenick.quacc.core.accounting;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.Date;

import de.nenick.quacc.core.robolectric.RoboCoreTest;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.accounting.AccountingType;

import static org.assertj.core.api.Assertions.assertThat;

public class AddAccountingUcTest extends RoboCoreTest {

    @InjectMocks
    AddNewAccountingUc_ addNewAccountingUc;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldAddAccounting() {
        Date date = new Date();

        addNewAccountingUc.apply("Bar", "Übertrag", "Monatlich", "Miete", date, 1212, "money money");

        AccountingCursor accountings = new AccountingSelection().query(context.getContentResolver());
        assertThat(accountings.getCount()).isEqualTo(1);
        accountings.moveToNext();
        assertThat(accountings.getAccountName()).isEqualTo("Bar");
        assertThat(accountings.getAccountingType()).isEqualTo(AccountingType.Übertrag);
        assertThat(accountings.getAccountingInterval()).isEqualTo(AccountingInterval.Monatlich);
        assertThat(accountings.getAccountingCategoryName()).isEqualTo("Miete");
        assertThat(accountings.getAccountingDate()).isEqualTo(date);
        assertThat(accountings.getValue()).isEqualTo(1212);
        assertThat(accountings.getComment()).isEqualTo("money money");
    }

}