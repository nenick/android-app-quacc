package de.nenick.quacc.test.scenarios;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class StartupSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testInitialOpenAndCloseApplication() {
        launcherPage.clickStartApp();

        // should start with closed drawer
        bookingEntriesPage.checkIsVisible();
        bookingEntriesPage.drawer().checkIsHidden();

        // should initial select an account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().checkIsVisible();
        bookingEntriesPage.drawer().accountGirokonto().checkIsSelected();
        bookingEntriesPage.drawer().accountBar().checkIsNotSelected();

        // drawer is closed after account selection
        bookingEntriesPage.drawer().accountBar().click();
        bookingEntriesPage.drawer().checkIsHidden();

        // should only mark new selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().checkIsSelected();
        bookingEntriesPage.drawer().accountGirokonto().checkIsNotSelected();

        // should close app with back press
        device.clickBackButton();
        launcherPage.checkIsVisible();
    }
}
