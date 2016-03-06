package de.nenick.quacc.test.issues;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookingEntriesActivityRotateSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testRestoreInstanceState() {
        launcherPage.clickStartApp();
        bookingEntriesPage.drawer().open();
        device.rotate();
        bookingEntriesPage.drawer().checkIsVisible();
    }
}
