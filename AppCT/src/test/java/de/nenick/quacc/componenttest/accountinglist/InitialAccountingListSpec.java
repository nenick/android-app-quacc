package de.nenick.quacc.componenttest.accountinglist;

import org.junit.Test;

import de.nenick.quacc.accountinglist.AccountingListActivity_;
import de.nenick.quacc.accountinglist.AccountingListFragment_;
import de.nenick.quacc.accountinglist.RoboAccountingListPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class InitialAccountingListSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();

    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void shouldShowAcountings() {
        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }
}
