package de.nenick.quacc.test.pages;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoDummyLauncherPage {

    public void isActivePage() {
        onView(withId(R.id.btn_start_app)).check(matches(isDisplayed()));
    }

    public void clickStartApp() {
        onView(withId(R.id.btn_start_app)).perform(click());
    }
}
