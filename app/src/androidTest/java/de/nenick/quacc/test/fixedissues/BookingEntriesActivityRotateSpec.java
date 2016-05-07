package de.nenick.quacc.test.fixedissues;

import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspDevice;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

@Ignore
public class BookingEntriesActivityRotateSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspDevice device = EspDevice.root();

    @After
    public void reset() {
        device.rotateToPortrait();
    }

    @Test
    public void testRestoreInstanceState() {
        device.assertOrientationIsPortrait();

        // select not initial selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().assertIsNotSelected();
        bookingEntriesPage.drawer().accountBar().click();
        bookingEntriesPage.drawer().open();

        // should remember drawer state after configuration change
        device.rotateToLandscape();
        bookingEntriesPage.drawer().assertIsVisible();
        bookingEntriesPage.drawer().accountBar().assertIsSelected();
    }
}
