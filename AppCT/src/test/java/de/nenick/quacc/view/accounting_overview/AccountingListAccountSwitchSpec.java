package de.nenick.quacc.view.accounting_overview;

import org.junit.Test;

import java.util.Date;

import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingDb_;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;
import jdk.nashorn.internal.ir.annotations.Ignore;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListAccountSwitchSpec extends RoboComponentTestBase {

    String defaultYear = "2015";
    Date defaultDate = QuAccDateUtil.toDate("5.5." + defaultYear);

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    @Ignore
    public void shouldSwitchAccount() {
        givenSomeEntriesForEachAccount();
        whenPageIsStarted();
        assertThat(accountingListPage.actionbar().title()).isEqualTo("Übersicht (Girokonto)");

        accountingListPage.drawer().open();
        assertThat(accountingListPage.actionbar().title()).isEqualTo("QuAcc Dev");
        accountingListPage.drawer().accountList().entries().get(0).isSelected();

        accountingListPage.drawer().accountList().entries().get(0).click();
        accountingListPage.drawer().close();
        assertThat(accountingListPage.actionbar().title()).isEqualTo("Übersicht (Girokonto)");

        accountingListPage.drawer().open();
        accountingListPage.drawer().accountList().entries().get(0).isSelected();
        accountingListPage.drawer().accountList().entries().get(1).click();
        accountingListPage.drawer().close();
        assertThat(accountingListPage.actionbar().title()).isEqualTo("Übersicht (Bar)");

        accountingListPage.drawer().open();
        accountingListPage.drawer().accountList().entries().get(1).isSelected();
        accountingListPage.drawer().accountList().entries().get(2).click();
        accountingListPage.drawer().close();
        assertThat(accountingListPage.actionbar().title()).isEqualTo("Übersicht (Tagesgeldkonto)");
    }

    private void givenSomeEntriesForEachAccount() {
        AccountingDb_.getInstance_(context).insert(1, AccountingType.incoming.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 1", 2);
        AccountingDb_.getInstance_(context).insert(2, AccountingType.outgoing.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 2", 40);
        AccountingDb_.getInstance_(context).insert(3, AccountingType.transfer.name(), AccountingInterval.once.name(), 1, defaultDate, "my comment 3", 600000);
    }

    private void whenPageIsStarted() {
        accountingListPage.startPage();
    }
}
