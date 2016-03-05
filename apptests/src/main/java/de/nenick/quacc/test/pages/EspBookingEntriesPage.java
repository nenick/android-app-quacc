package de.nenick.quacc.test.pages;


import de.nenick.quacc.R;
import android.support.test.espresso.contrib.DrawerActions;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.CoreMatchers.not;

public class EspBookingEntriesPage {

    public void isActivePage() {
        onView(withId(R.id.fragment_booking_entries)).check(matches(isDisplayed()));
        onView(withId(R.id.appbar)).check(matches(isDisplayed()));
    }

    public void clickAddButton() {
        onView(withId(R.id.btn_add_accounting)).perform(click());
    }

    public EspressoAccountingList list() {
        return new EspressoAccountingList();
    }

    public BookingEntriesDrawer drawer() {
        return new BookingEntriesDrawer();
    }

    public static class BookingEntriesDrawer {

        public void open() {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.open());
            onView(withId(R.id.navigation_drawer)).check(matches(isDisplayed()));
        }

        public void close() {
            onView(withId(R.id.drawer_layout)).perform(DrawerActions.close());
            onView(withId(R.id.navigation_drawer)).check(matches(not(isDisplayed())));
        }
    }
}
