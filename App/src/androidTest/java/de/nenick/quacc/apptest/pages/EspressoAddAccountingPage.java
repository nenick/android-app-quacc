package de.nenick.quacc.apptest.pages;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoAddAccountingPage {

    public void isActivePage() {
        onView(withId(R.id.btn_speech_recognition)).check(matches(isDisplayed()));
    }
}
