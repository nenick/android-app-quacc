package de.nenick.espressopages.pages;

import android.support.design.internal.NavigationMenuItemView;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.view.View;
import android.view.ViewGroup;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.instanceOf;

public class EspNavigationMenuItem {

    private String itemText;

    public EspNavigationMenuItem(String itemText) {
        this.itemText = itemText;
    }

    public void click() {
        findView().perform(ViewActions.click());
    }

    public void checkIsSelected() {
        findView().check(matches(withChild(isChecked())));
    }

    public void checkIsNotSelected() {
        findView().check(matches(withChild(isNotChecked())));
    }

    private ViewInteraction findView() {
        return onView(allOf(instanceOf(NavigationMenuItemView.class), withChildGroups(withText(itemText)), isDisplayed()));
    }

    private static Matcher<View> withChildGroups(final Matcher<View> childMatcher) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("has child: ");
                childMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof ViewGroup)) {
                    return false;
                }

                ViewGroup group = (ViewGroup) view;
                for (int i = 0; i < group.getChildCount(); i++) {
                    View childView = group.getChildAt(i);
                    if (childMatcher.matches(childView)) {
                        return true;
                    }
                    // check also recursively
                    if(matchesSafely(childView)) {
                        return true;
                    }
                }

                return false;
            }
        };
    }
}
