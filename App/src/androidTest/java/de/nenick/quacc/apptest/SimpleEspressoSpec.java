package de.nenick.quacc.apptest;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SimpleEspressoSpec extends BaseEspressoSpec {

    public void testSomething() {
        startApp();
        onView(ViewMatchers.withId(R.id.listView)).check(matches(isDisplayed()));
    }

    public void testBackPress() {
        startApp();
        Espresso.pressBack();
        onView(withId(R.id.btn_start_app)).check(matches(isDisplayed()));
    }

}
