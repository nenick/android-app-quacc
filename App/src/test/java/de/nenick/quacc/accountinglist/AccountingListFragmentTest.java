package de.nenick.quacc.accountinglist;

import org.junit.Test;

import de.nenick.quacc.MainActivity_;
import de.nenick.quacc.MainFragment_;
import de.nenick.quacc.TestQuAccApplication;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

public class AccountingListFragmentTest extends RobolectricSupportedTest {

    RoboSup<AccountingListActivity_, AccountingListFragment_> robo = new RoboSup<>();
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(robo);

    @Test
    public void checkList(){
        given(TestQuAccApplication.coreModuleMocks.getAccountingListUc.apply()).willReturn(new CharSequence[]{"Eintrag 1", "Eintrag 2", "Eintrag 3"});
        accountingListPage.startPage();

        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }
}