package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.data.TestData;
import de.nenick.quacc.test.pages.EspAddBookingEntryPage;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class GroupBookingEntriesSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();
    EspAddBookingEntryPage addBookingEntryPage = new EspAddBookingEntryPage();

    @Before
    public void setup() {
        dummyAppLauncher.click();
    }

    @Test
    public void testGroupByIncomingAndOutgoing() {
        // given booking entries as incoming
        TestData.bookingEntry().direction(BookingDirectionOption.incoming).create();
        TestData.bookingEntry().direction(BookingDirectionOption.incoming).create();

        // given booking entries as outgoing
        TestData.bookingEntry().direction(BookingDirectionOption.outgoing).create();

        // booking entries are grouped
        bookingEntriesPage.list().assertItemCountIs(2);

        ReferencePicuture.check("booking groups closed");

        // show booking entries after expanding first group
        bookingEntriesPage.list().itemByVisibleIndex(0).click();
        bookingEntriesPage.list().assertItemCountIs(4);

        // show booking entries after expanding first group
        bookingEntriesPage.list().itemByVisibleIndex(3).click();
        bookingEntriesPage.list().assertItemCountIs(5);

        ReferencePicuture.check("booking groups opened");
    }
}