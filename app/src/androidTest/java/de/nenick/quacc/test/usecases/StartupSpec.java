package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspDevice;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;
import de.nenick.quacc.test.pages.EspDummyLauncherPage;

public class StartupSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspDummyLauncherPage launcherPage = new EspDummyLauncherPage();
    EspDevice device = EspDevice.root();

    @Before
    public void setup() {
        dummyAppLauncher.click();
    }

    @Test
    public void testInitialOpenAndCloseApplication() {
        // should start with closed drawer
        bookingEntriesPage.assertIsVisible();
        bookingEntriesPage.drawer().assertIsHidden();

        // has no booking entries
        bookingEntriesPage.list().assertItemCountIs(0);

        ReferencePicuture.check("initial startup");

        // should close app with back press
        device.clickBackButton();
        launcherPage.assertIsVisible();
    }
}
