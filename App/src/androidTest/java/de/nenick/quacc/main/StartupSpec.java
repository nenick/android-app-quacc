package de.nenick.quacc.main;

import android.support.test.espresso.Espresso;

import de.nenick.quacc.BaseEspressoSpec;
import de.nenick.quacc.accountinglist.EspressoAccountingListPage;

public class StartupSpec extends BaseEspressoSpec {

    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();
    EspressoAccountingListPage accountingListPage = new EspressoAccountingListPage();

    public void testStartup() {
        startApp();
        accountingListPage.isActivePage();
        Espresso.pressBack();
        launcherPage.isActivePage();
    }
}
