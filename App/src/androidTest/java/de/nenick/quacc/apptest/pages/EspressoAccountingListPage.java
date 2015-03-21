package de.nenick.quacc.apptest.pages;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoAccountingListPage {

    public void isActivePage() {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    public void clickAddButton() {
        onView(withId(R.id.btn_add_accounting)).perform(click());
    }
}
