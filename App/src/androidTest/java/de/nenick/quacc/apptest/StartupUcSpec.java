package de.nenick.quacc.apptest;

import android.support.test.espresso.Espresso;

import org.junit.Test;

import de.nenick.quacc.apptest.espresso.BaseEspressoSpec;
import de.nenick.quacc.apptest.pages.EspressoAccountingListPage;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

public class StartupUcSpec extends BaseEspressoSpec {

    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();
    EspressoAccountingListPage accountingListPage = new EspressoAccountingListPage();

    @Test
    public void shouldCloseAppOnBackPressFromAccountingList() {
        startApp();
        accountingListPage.isActivePage();
        Espresso.pressBack();
        launcherPage.isActivePage();
    }
}
