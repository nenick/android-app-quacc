package de.nenick.quacc.apptest.pages;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.DatePicker;

import org.hamcrest.Matcher;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.Is.is;

public class EspressoAddAccountingPage {

    public void isActivePage() {
        onView(withId(R.id.btn_speech_recognition)).check(matches(isDisplayed()));
    }

    public void clickSpeechRecogniction() {
        onView(withId(R.id.btn_speech_recognition)).perform(click());
    }

    public void chooseAccount(String text) {
        onView(withId(R.id.account)).perform(click());
        onView(withText(text)).perform(click());
    }

    public void chooseAccountingInterval(String text) {
        onView(withId(R.id.interval)).perform(click());
        onView(withText(text)).perform(click());
    }

    public void chooseAccountingType(String text) {
        onView(withId(R.id.accountingType)).perform(click());
        onView(withText(text)).perform(click());
    }

    public void chooseAccountingCategory(String text) {
        onView(withId(R.id.category)).perform(click());
        onView(withText(text)).perform(click());
    }

    public void chooseAccountingValue(String text) {
        onView(withId(R.id.value)).perform(typeText(text));
    }

    public void chooseAccountingDate(String date) {
        onView(withId(R.id.date)).perform(click());
        onView(withClassName(is(DatePicker.class.getName()))).perform(setDate(10, 11, 2012));
        onView(withId(android.R.id.button1)).perform(click());
    }

    private static ViewAction setDate(final int day, final int month, final int year) {
        return new ViewAction() {
            @Override
            public void perform(UiController uiController, View view) {
                ((DatePicker) view).updateDate(year, month, day);
            }

            @Override
            public String getDescription() {
                return "Set the date into the datepicker(day, month, year)";
            }

            @Override
            public Matcher<View> getConstraints() {
                return ViewMatchers.isAssignableFrom(DatePicker.class);
            }
        };
    }

    public EspressoAddAccountingActionbar actionbar() {
        return new EspressoAddAccountingActionbar();
    }
}
