package de.nenick.quacc.test.requirements.bookingentriesactivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspDevice;
import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.quacc.R;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.data.TestData;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class RotateSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspDevice device = EspDevice.root();

    @Before
    public void setup() {
        launcherPage.clickStartApp();
    }

    @After
    public void reset() {
        device.rotateToPortrait();
    }

    @Test
    public void testDrawerState() {
        // select not initial selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().assertIsNotSelected();
        bookingEntriesPage.drawer().accountBar().click();

        // open drawer and rotate screen
        bookingEntriesPage.drawer().assertIsHidden();
        bookingEntriesPage.drawer().open();
        device.rotateToLandscape();

        // should remember drawer state after configuration change
        bookingEntriesPage.drawer().assertIsVisible();
        bookingEntriesPage.drawer().accountBar().assertIsSelected();
    }

    @Test
    public void testListState() {
        // select not initial selected account
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountBar().assertIsNotSelected();
        bookingEntriesPage.drawer().accountBar().click();

        // given booking group
        TestData.bookingEntry().account(TestData.Account.Bar).create();
        TestData.bookingEntry().account(TestData.Account.Bar).create();

        // expand the booking group
        bookingEntriesPage.list().itemByVisibleIndex(0).click();
        bookingEntriesPage.list().assertItemCountIs(3);

        // rotate screen
        device.rotateToLandscape();

        // booing group should still be expanded
        bookingEntriesPage.list().assertItemCountIs(3);
    }

    @Test
    public void testToolbarState() {
        // show extended toolbar
        bookingEntriesPage.toolbar().report().assertIsDisplayedOnScreen();

        // still showing after rotation
        device.rotateToLandscape();
        bookingEntriesPage.toolbar().report().assertIsDisplayedOnScreen();

        // hide extended toolbar
        EspPage.byId(R.id.activity_booking_entries).swipeUp();
        bookingEntriesPage.toolbar().report().assertIsHidden();

        // still hidden after rotation
        device.rotateToPortrait();
        bookingEntriesPage.toolbar().report().assertIsHidden();
    }

    @Test
    public void testSwitchDateState() {
        TestData.bookingEntry().create();
        bookingEntriesPage.list().assertItemCountIs(1);
        bookingEntriesPage.switchDate().assertIsHidden();

        bookingEntriesPage.toolbar().switchDateToggle().click();
        bookingEntriesPage.switchDate().assertIsDisplayedOnScreen();

        bookingEntriesPage.switchDate().monthUp().click();
        bookingEntriesPage.list().assertItemCountIs(0);

        device.rotateToLandscape();
        bookingEntriesPage.switchDate().assertIsDisplayedOnScreen();
        bookingEntriesPage.list().assertItemCountIs(0);
    }
}
