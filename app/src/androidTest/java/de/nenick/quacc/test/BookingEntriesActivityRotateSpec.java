package de.nenick.quacc.test;

import org.junit.Test;

import de.nenick.espressomacchiato.testbase.EspDummyLauncherActivity;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookingEntriesActivityRotateSpec extends QuAccEspTestCase<BaseActivity> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testRestoreInstanceState() {
        //launcherPage.clickStartApp();
        //device.assertOrientationIsPortrait();

        // select not initial selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().assertIsNotSelected();
        bookingEntriesPage.drawer().accountBar().click();
        bookingEntriesPage.drawer().open();

        // should remember drawer state after configuration change
        //device.rotateToLandscape();
        bookingEntriesPage.drawer().assertIsVisible();
        bookingEntriesPage.drawer().accountBar().assertIsSelected();
    }
}
