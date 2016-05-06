package de.nenick.quacc.test.pages;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.espresso.matcher.ViewMatchers;
import android.view.View;
import android.widget.DatePicker;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import de.nenick.espressomacchiato.elements.EspPage;
import de.nenick.quacc.R;
import de.nenick.quacc.database.provider.category.CategoryCursor;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

public class EspAddBookingEntryPage extends EspPage {

    public EspAddBookingEntryPage() {
        super(R.id.fragment_add_booking_entry);
    }

    public void clickSpeechRecogniction() {
        onView(withId(R.id.btn_speech_recognition)).perform(ViewActions.click());
    }

    public void chooseAccount(String text) {
        onView(withId(R.id.account)).perform(ViewActions.click());
        onView(withText(text)).perform(ViewActions.click());
    }

    public void chooseAccountingInterval(String text) {
        onView(withId(R.id.interval)).perform(ViewActions.click());
        onView(withText(text)).perform(ViewActions.click());
    }

    public void chooseBookingDirection(String text) {
        onView(withId(R.id.direction)).perform(ViewActions.click());
        onView(withText(text)).perform(ViewActions.click());
    }

    public void chooseAccountingCategory(final String text) {
        onView(withId(R.id.category)).perform(ViewActions.click());
        onData(allOf(is(instanceOf(CategoryCursor.class)), withItemContent(text))).perform(ViewActions.click());
    }

    public static Matcher<Object> withItemContent(String expectedText) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(expectedText);
        return withItemContent(equalTo(expectedText));
    }

    public static Matcher<Object> withItemContent(final Matcher<String> itemTextMatcher) {
        // use preconditions to fail fast when a test is creating an invalid matcher.
        checkNotNull(itemTextMatcher);
        return new BoundedMatcher<Object, CategoryCursor>(
                CategoryCursor.class) {

            @Override
            public void describeTo(Description description) {
                itemTextMatcher.describeTo(description);
            }

            @Override
            protected boolean matchesSafely(CategoryCursor arg0) {
                return itemTextMatcher.matches(arg0.getName());
            }
        };
    }

    public void ammount(String text) {
        onView(withId(R.id.amount)).perform(typeText(text));
    }

    public void chooseAccountingDate(String date) {
        onView(withId(R.id.date)).perform(ViewActions.click());
        onView(withClassName(is(DatePicker.class.getName()))).perform(setDate(10, 11, 2012));
        onView(withId(android.R.id.button1)).perform(ViewActions.click());
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
