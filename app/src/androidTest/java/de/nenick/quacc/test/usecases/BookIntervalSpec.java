package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.pages.EspAddBookingEntryPage;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookIntervalSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

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
        addBookingEntryPage.chooseAccountingInterval("Täglich");
        addBookingEntryPage.ammount("40");

        // confirm booking entry
        addBookingEntryPage.actionbar().clickConfirmButton();

        // account has all daily entries
        bookingEntriesPage.list().assertItemCountIs(1);
        bookingEntriesPage.list().itemByVisibleIndex(0).click();
        int daysUntilMonthEnd = quAccDateUtil.today().dayOfMonth().getMaximumValue() - quAccDateUtil.today().dayOfMonth().get() + 1;
        bookingEntriesPage.list().assertItemCountIs(1 + daysUntilMonthEnd);

        // account has all daily entries for next month
        bookingEntriesPage.toolbar().switchDateToggle().click();
        bookingEntriesPage.switchDate().monthUp().click();

        bookingEntriesPage.list().assertItemCountIs(1);
        bookingEntriesPage.list().itemByVisibleIndex(0).click();
        daysUntilMonthEnd = quAccDateUtil.today().plusMonths(1).dayOfMonth().getMaximumValue();
        bookingEntriesPage.list().assertItemCountIs(1 + daysUntilMonthEnd);
    }

    @Test
    public void testAddBookingEntryWithEndDate() {
        // open add booking entry page
        bookingEntriesPage.list().assertItemCountIs(0);
        bookingEntriesPage.addButton().click();
        addBookingEntryPage.assertIsDisplayedOnScreen();

        // set mandatory properties
        addBookingEntryPage.chooseAccountingInterval("Täglich");
        addBookingEntryPage.ammount("40");

        // set end date
        addBookingEntryPage.endDateCheckbox().click();
        addBookingEntryPage.chooseEndDate(28, 4, 2016);

        // confirm booking entry
        addBookingEntryPage.actionbar().clickConfirmButton();

        // account has new entry
        bookingEntriesPage.list().assertItemCountIs(1);
        bookingEntriesPage.list().itemByVisibleIndex(0).click();
        int expected = 28 - quAccDateUtil.today().getDayOfMonth() + 1;
        bookingEntriesPage.list().assertItemCountIs(1 + expected);

        // account has no more entries
        bookingEntriesPage.toolbar().switchDateToggle().click();
        bookingEntriesPage.switchDate().monthUp().click();
        bookingEntriesPage.list().assertItemCountIs(0);
    }
}
