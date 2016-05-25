package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.R;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspAddBookingEntryPage;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookIncomingSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspAddBookingEntryPage addBookingEntryPage = new EspAddBookingEntryPage();

    @Before
    public void setup() {
        launcherPage.clickStartApp();
    }

    @Test
    public void testAddBookingEntry() {
        // open add booking entry page
        bookingEntriesPage.list().assertItemCountIs(0);
        bookingEntriesPage.addButton().click();
        addBookingEntryPage.assertIsDisplayedOnScreen();

        // set mandatory properties
        addBookingEntryPage.chooseBookingDirection("Einnahme");
        addBookingEntryPage.ammount("40");

        // confirm booking entry
        addBookingEntryPage.actionbar().clickConfirmButton();

        // account has new entry
        bookingEntriesPage.list().assertItemCountIs(1);

        // entry is marked as incoming
        bookingEntriesPage.list().itemByVisibleIndex(0).category().assertTextColorResIs(R.color.positiveText);
        bookingEntriesPage.list().itemByVisibleIndex(0).date().assertTextColorResIs(R.color.positiveTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).dateSeparator().assertTextColorResIs(R.color.positiveTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).endDate().assertTextColorResIs(R.color.positiveTextSmall);
        bookingEntriesPage.list().itemByVisibleIndex(0).amount().assertTextColorResIs(R.color.positiveText);
    }
}
