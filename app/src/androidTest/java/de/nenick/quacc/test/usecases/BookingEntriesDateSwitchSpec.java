package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.espressomacchiato.tools.EspWait;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.data.TestData;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookingEntriesDateSwitchSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Before
    public void setup() {
        dummyAppLauncher.click();
    }

    @Test
    public void testSwitchDate() {
        bookingEntriesPage.toolbar().switchDateToggle().click();

        ReferencePicuture.take("date switch expanded");

        TestData.bookingEntry().create();
        EspWait.forDelay(1000);
        bookingEntriesPage.list().assertItemCountIs(1);

        bookingEntriesPage.switchDate().month().assertTextIs("April");
        bookingEntriesPage.switchDate().monthUp().click();
        bookingEntriesPage.switchDate().month().assertTextIs("Mai");
        bookingEntriesPage.list().assertItemCountIs(0);

        bookingEntriesPage.switchDate().monthDown().click();
        bookingEntriesPage.switchDate().month().assertTextIs("April");
        bookingEntriesPage.list().assertItemCountIs(1);

        bookingEntriesPage.switchDate().year().assertTextIs("2016");
        bookingEntriesPage.switchDate().yearUp().click();
        bookingEntriesPage.switchDate().year().assertTextIs("2017");
        bookingEntriesPage.list().assertItemCountIs(0);

        bookingEntriesPage.switchDate().yearDown().click();
        bookingEntriesPage.switchDate().year().assertTextIs("2016");
        bookingEntriesPage.list().assertItemCountIs(1);
    }
}
