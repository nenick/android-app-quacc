package de.nenick.quacc.test.usecase;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.R;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspAddBookingEntryPage;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookIntervalDailySpec extends QuAccEspTestCase<DummyLauncherActivity_> {

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
        addBookingEntryPage.chooseAccountingInterval("Täglich");
        addBookingEntryPage.ammount("40");

        // confirm booking entry
        addBookingEntryPage.actionbar().clickConfirmButton();

        // account has new entry
        bookingEntriesPage.list().assertItemCountIs(1);
        bookingEntriesPage.list().itemByVisibleIndex(0).click();

        int daysUntilMonthEnd = DateTime.now().dayOfMonth().getMaximumValue() - DateTime.now().dayOfMonth().get();
        bookingEntriesPage.list().assertItemCountIs(1 + daysUntilMonthEnd);
    }
}
