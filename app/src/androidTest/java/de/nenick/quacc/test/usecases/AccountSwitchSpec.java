package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.data.TestData;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class AccountSwitchSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Before
    public void setup() {
        dummyAppLauncher.click();
    }

    @Test
    public void testSwitchBetweenAccounts() {
        // given account Girokonto without booking entries
        bookingEntriesPage.list().assertItemCountIs(0);

        // given account Bar with one booking entry
        TestData.bookingEntry().account(TestData.Account.Bar).create();

        // drawer shows available accounts
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountGirokonto().assertIsDisplayedOnScreen();
        bookingEntriesPage.drawer().accountBar().assertIsDisplayedOnScreen();
        bookingEntriesPage.drawer().accountKreditkarte().assertIsDisplayedOnScreen();

        ReferencePicuture.take("open drawer");

        // initial account is selected
        bookingEntriesPage.drawer().accountGirokonto().assertIsSelected();

        // close drawer when another account is selected
        bookingEntriesPage.drawer().accountBar().click();
        bookingEntriesPage.drawer().assertIsHidden();

        // account Bar is shown
        bookingEntriesPage.list().assertItemCountIs(1);

        // current account is selected
        bookingEntriesPage.drawer().open();
        bookingEntriesPage.drawer().accountGirokonto().assertIsNotSelected();
        bookingEntriesPage.drawer().accountBar().assertIsSelected();

        // switch back to Girokonto
        bookingEntriesPage.drawer().accountGirokonto().click();
        bookingEntriesPage.list().assertItemCountIs(0);
    }


}
