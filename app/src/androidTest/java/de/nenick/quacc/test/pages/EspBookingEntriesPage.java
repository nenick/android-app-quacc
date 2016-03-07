package de.nenick.quacc.test.pages;


import de.nenick.quacc.R;
import de.nenick.espressopages.pages.EspPage;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspBookingEntriesPage extends EspPage {

    @Override
    public void checkIsVisible() {
        onView(withId(R.id.appbar)).check(matches(isDisplayed()));
        onView(withId(R.id.fab)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_booking_entries)).check(matches(isDisplayed()));
    }

    public void clickAddButton() {
        onView(withId(R.id.btn_add_accounting)).perform(click());
    }

    public EspressoAccountingList list() {
        return new EspressoAccountingList();
    }

    public EspBookingEntriesDrawer drawer() {
        return new EspBookingEntriesDrawer(R.id.drawer_layout, R.id.navigation_drawer);
    }
}
