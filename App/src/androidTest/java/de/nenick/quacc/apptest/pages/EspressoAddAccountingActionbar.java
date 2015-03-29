package de.nenick.quacc.apptest.pages;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class EspressoAddAccountingActionbar {
    public void clickConfirmButton() {
        onView(withId(R.id.confirm)).perform(click());
    }
}
