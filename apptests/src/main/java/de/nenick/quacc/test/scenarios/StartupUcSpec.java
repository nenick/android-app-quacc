package de.nenick.quacc.test.scenarios;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class StartupUcSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void shouldCloseAppOnBackPressFromAccountingList() {
        launcherPage.isActivePage();
        launcherPage.clickStartApp();

        bookingEntriesPage.isActivePage();
        bookingEntriesPage.drawer().open();
        // TODO check for selection account
        bookingEntriesPage.drawer().close();

        device.clickBackButton();
        launcherPage.isActivePage();
    }
}
