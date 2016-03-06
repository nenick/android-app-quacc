package de.nenick.quacc.test.scenarios;

import org.junit.Test;

import de.nenick.quacc.test.espresso.EspressoTestCase;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class SwitchAccountSpec extends EspressoTestCase {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Test
    public void testNothing() {
        launcherPage.clickStartApp();
        bookingEntriesPage.drawer().open();

    }
}
