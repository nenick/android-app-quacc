package de.nenick.quacc.apptest.accountinglist;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoAccountingListPage {

    public void isActivePage() {
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }
}
