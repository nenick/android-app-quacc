package de.nenick.quacc.specs.main;

import org.junit.Test;

import de.nenick.quacc.view.accounting_overview.RoboAccountingListPage;
import de.nenick.quacc.view.main.MainActivity_;
import de.nenick.quacc.view.main.MainFragment_;
import de.nenick.quacc.view.main.RoboMainPage;
import de.nenick.robolectric.RoboComponentTestBase;
import de.nenick.robolectric.RoboSup;

import static org.assertj.core.api.Assertions.assertThat;

public class MainFragmentSpec extends RoboComponentTestBase {

    RoboSup<MainActivity_, MainFragment_> robo = new RoboSup<>();
    RoboMainPage mainPage = new RoboMainPage(robo);

    @Test
    public void shouldStartNextPage() {
        mainPage.startPage();
        assertThat(mainPage.nextStartedPage()).isEqualTo(RoboAccountingListPage.Intent());
    }
}