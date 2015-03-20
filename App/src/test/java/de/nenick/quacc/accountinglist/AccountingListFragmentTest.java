package de.nenick.quacc.accountinglist;

import org.junit.Test;

import de.nenick.quacc.MainActivity_;
import de.nenick.quacc.MainFragment_;
import de.nenick.robolectric.RoboSupTest;
import de.nenick.robolectric.RobolectricSupportedTest;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountingListFragmentTest extends RobolectricSupportedTest {

    RoboSupTest<MainActivity_, MainFragment_> roboSupTest = new RoboSupTest<MainActivity_, MainFragment_>() {
    };
    RoboAccountingListPage accountingListPage = new RoboAccountingListPage(roboSupTest);

    @Test
    public void checkList(){
        accountingListPage.startPage();

        assertThat(accountingListPage.list().count()).isEqualTo(3);
    }
}