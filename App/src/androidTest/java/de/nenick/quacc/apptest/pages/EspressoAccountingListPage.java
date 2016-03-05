package de.nenick.quacc.apptest.pages;

import android.support.test.espresso.contrib.DrawerActions;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoAccountingListPage {

    public void isActivePage() {
        onView(withId(R.id.listViewExpandable)).check(matches(isDisplayed()));
    }

    public void clickAddButton() {
        onView(withId(R.id.btn_add_accounting)).perform(click());
    }

    public EspressoAccountingList list() {
        return new EspressoAccountingList();
    }

    public void closeDrawer() {
        DrawerActions.closeDrawer(R.id.drawer_layout);
    }
}
