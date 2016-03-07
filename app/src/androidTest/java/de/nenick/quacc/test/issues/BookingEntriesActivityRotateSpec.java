package de.nenick.quacc.test.issues;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookingEntriesActivityRotateSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testRestoreInstanceState() {
        launcherPage.clickStartApp();

        // select not initial selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().checkIsNotSelected();
        bookingEntriesPage.drawer().accountBar().click();
        bookingEntriesPage.drawer().open();

        // should remember drawer state after configuration change
        device.rotate();
        bookingEntriesPage.drawer().checkIsVisible();
        bookingEntriesPage.drawer().accountBar().checkIsSelected();
    }
}
