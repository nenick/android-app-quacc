package de.nenick.quacc.scenarios;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction;
import de.nenick.quacc.core.accounting.creation.CreateAccountingFunction_;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.database.accounting.AccountingDb_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.view.accounting_edit.RoboEditAccountingPage;
import de.nenick.quacc.view.accounting_overview.RoboAccountingListPage;
import de.nenick.robolectric.RoboComponentTestBase;

import static org.assertj.core.api.Assertions.assertThat;

public class EditAccountingScenarioSpec extends RoboComponentTestBase {

    RoboAccountingListPage roboAccountingListPage = new RoboAccountingListPage();
    RoboEditAccountingPage roboEditAccountingPage = new RoboEditAccountingPage();
    int firstValue = 500;
    int editedValue = 1000;
    long accountingId;
    String editedValueString = String.valueOf(editedValue);
    CreateAccountingFunction createAccountingFunction;
    AccountingDb accountingDb;

    @Before
    public void setup() {
        createAccountingFunction = CreateAccountingFunction_.getInstance_(context);
        accountingDb = AccountingDb_.getInstance_(context);
    }

    @Test
    public void testEditAccounting() {
        givenAccountingWith(firstValue);
        givenEditAccountingView();
        roboEditAccountingPage.valueField().setText(editedValueString);
        roboEditAccountingPage.actionbar().confirmMenuItem().click();
        thenAccountingValueIsUpdated();
    }

    private void thenAccountingValueIsUpdated() {
        AccountingCursor accountingCursor = accountingDb.getById(accountingId);
        accountingCursor.moveToNext();
        assertThat(accountingCursor.getValue()).isEqualTo(editedValue);
        accountingCursor.close();
    }

    private void givenEditAccountingView() {
        roboAccountingListPage.startPage();
        roboAccountingListPage.list().entries().get(0).select();
    }

    private void givenAccountingWith(int value) {
        accountingId = createAccountingFunction.apply(
                "Girokonto",
                AccountingType.incoming.name(),
                AccountingInterval.once.name(),
                1,
                new Date(),
                value,
                "");
    }
}
