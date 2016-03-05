package de.nenick.quacc.test.scenarios;

import android.support.test.espresso.Espresso;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspressoAccountingListPage;
import de.nenick.quacc.test.pages.EspressoDummyLauncherPage;

public class StartupUcSpec extends EspressoTestCase {

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
