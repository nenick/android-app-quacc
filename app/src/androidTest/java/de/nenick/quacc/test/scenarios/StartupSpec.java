package de.nenick.quacc.test.scenarios;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class StartupSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testInitialOpenAndCloseApplication() {
        launcherPage.clickStartApp();

        bookingEntriesPage.checkIsVisible();
        bookingEntriesPage.drawer().checkIsHidden();

        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().checkIsVisible();
        //bookingEntriesPage.drawer().checkNavigationMenuItemIsSelected("Girokonto");
        bookingEntriesPage.drawer().clickNavigationMenuItem("Bar");
        bookingEntriesPage.drawer().checkNavigationMenuItemIsSelected("Bar");

        device.clickBackButton();
        launcherPage.checkIsVisible();
    }
}
