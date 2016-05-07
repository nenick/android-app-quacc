package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.R;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspAddBookingEntryPage;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookOutgoingSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspAddBookingEntryPage addBookingEntryPage = new EspAddBookingEntryPage();

    @Before
    public void setup() {
        dummyAppLauncher.click();
    }

    @Test
    public void testAddBookingEntry() {
        // open add booking entry page
        bookingEntriesPage.list().assertItemCountIs(0);
        bookingEntriesPage.addButton().click();
        addBookingEntryPage.assertIsDisplayedOnScreen();

        // set mandatory properties
        addBookingEntryPage.ammount("40");

        // confirm booking entry
        addBookingEntryPage.actionbar().clickConfirmButton();

        // account has new entry
        bookingEntriesPage.list().assertItemCountIs(1);

        // entry is marked as outgoing
        bookingEntriesPage.list().itemByVisibleIndex(0).category().assertTextColorResIs(R.color.negativeText);
        bookingEntriesPage.list().itemByVisibleIndex(0).date().assertTextColorResIs(R.color.negativeTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).dateSeparator().assertTextColorResIs(R.color.negativeTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).endDate().assertTextColorResIs(R.color.negativeTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).amount().assertTextColorResIs(R.color.negativeText);
    }
}
