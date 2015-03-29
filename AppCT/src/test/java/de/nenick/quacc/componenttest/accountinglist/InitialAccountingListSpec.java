package de.nenick.quacc.componenttest.accountinglist;

import com.getbase.android.forger.Forger;

import org.junit.Test;

import de.nenick.quacc.accountinglist.AccountingListActivity_;
import de.nenick.quacc.accountinglist.AccountingListFragment_;
import de.nenick.quacc.accountinglist.RoboAccountingListPage;
import de.nenick.quacc.componenttest.RoboComponentTestBase;
import de.nenick.quacc.database.provider.testdata.Accounting;
import de.nenick.quacc.database.provider.testdata.TestDataGraph;
import de.nenick.quacc.database.provider.testdata.base.DataModel;
import de.nenick.quacc.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.contentOf;

public class InitialAccountingListSpec extends RoboComponentTestBase {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();

    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    Forger<DataModel> access = TestDataGraph.access();

    @Test
    public void shouldShowAcountings() {
        access.iNeed(3).of(Accounting.class).in(context.getContentResolver());

        accountingListPage.startPage();
        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }
}
