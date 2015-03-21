package de.nenick.quacc.apptest.main;

import android.support.test.espresso.Espresso;

import de.nenick.quacc.apptest.BaseEspressoSpec;
import de.nenick.quacc.apptest.accountinglist.EspressoAccountingListPage;

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
