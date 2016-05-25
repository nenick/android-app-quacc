package de.nenick.quacc.test.usecases;

import org.junit.Before;
import org.junit.Test;

import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.espressomacchiato.elements.EspView;
import de.nenick.quacc.R;
import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.QuAccEspTestCase;
import de.nenick.quacc.test.ReferencePicuture;
import de.nenick.quacc.test.data.TestData;
import de.nenick.quacc.test.pages.EspBookingEntriesPage;

public class BookingEntriesListToolbarSpec extends QuAccEspTestCase<DummyLauncherActivity_> {

    EspBookingEntriesPage bookingEntriesPage = new EspBookingEntriesPage();

    @Before
    public void setup() {
        launcherPage.clickStartApp();
    }

    @Test
    public void testCollapseToolbar() {
        // given booking entry
        TestData.bookingEntry().create();

        // initial show toolbar extra information
        EspView.byId(R.id.report).assertIsDisplayedOnScreen();

        // collapse toolbar (swipe on fragment in coordinator layout does not work current)
        EspPage.byId(R.id.activity_booking_entries).swipeUp();

        // toolbar extra information are gone
        EspView.byId(R.id.report).assertIsHidden();

        ReferencePicuture.take("booking list toolbar collapsed");
    }
}