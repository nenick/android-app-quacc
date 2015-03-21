package de.nenick.quacc.apptest;

import android.test.ActivityInstrumentationTestCase2;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class BaseEspressoSpec extends ActivityInstrumentationTestCase2<DummyLauncherActivity_> {
    public BaseEspressoSpec() {
        super(DummyLauncherActivity_.class);
    }

    public void startApp() {
        getActivity();
        onView(withId(R.id.btn_start_app)).perform(click());
    }
}
