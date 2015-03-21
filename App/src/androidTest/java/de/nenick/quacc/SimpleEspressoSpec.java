package de.nenick.quacc;

import android.support.test.espresso.Espresso;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class SimpleEspressoSpec extends BaseEspressoSpec {

    public void testSomething() {
        startApp();
        onView(withId(R.id.listView)).check(matches(isDisplayed()));
    }

    public void testBackPress() {
        startApp();
        Espresso.pressBack();
        onView(withId(R.id.btn_start_app)).check(matches(isDisplayed()));
    }

}
