package de.nenick.quacc.test.espresso.pagepattern;

import android.support.annotation.StringRes;
import android.support.design.internal.NavigationMenuItemView;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.DrawerActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.view.View;
import android.widget.Checkable;
import android.widget.CheckedTextView;
import android.widget.TextView;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import de.nenick.quacc.R;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasSibling;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.both;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

public class EspDrawer {

    protected final int drawerLayout;
    protected final int drawerContent;

    public EspDrawer(int drawerLayout, int drawerContent) {
        this.drawerLayout = drawerLayout;
        this.drawerContent = drawerContent;
    }

    public void checkIsVisible() {
        onView(withId(drawerContent)).check(matches(isDisplayed()));
    }

    public void checkIsHidden() {
        onView(withId(drawerContent)).check(matches(not(isDisplayed())));
    }

    public void open() {
        onView(withId(drawerLayout)).perform(DrawerActions.open());
    }

    public void close() {
        onView(withId(drawerLayout)).perform(DrawerActions.close());
    }

    public void clickNavigationMenuItem(String text) {
        onView(withText(text)).perform(click());

        /*onData(is(instanceOf(NavigationMenuItemView.class)))
                .inAdapterView(withId(de.nenick.quacc.R.id.navigation_drawer))
                .atPosition(5)
                .check(matches(isChecked()))
                .perform(click());
*/
        //onData(allOf(is(instanceOf(NavigationMenuItemView.class)), withText(text))).perform(click());
    }

    public void checkNavigationMenuItemIsSelected(String text) {
        // -> NavigationMenuItemView
        // --> AppCompatCheckedTextView (has the is-checked state)
        // --> FrameLayout (child 1)
        // ListItemAccount_ (child 2)
        // RelativeLayout (child 3)
        // AppCompatTextView (child 4)
        // AppCompatTextView
        onView(allOf(instanceOf(NavigationMenuItemView.class), withChild(withChild(withChild(withChild(withText(text)))))))
                .check(matches(withChild(isChecked())));

        //onView(navigationMenuItem(text)).check(matches(isChecked()));
        //onData(allOf(is(instanceOf(NavigationMenuItemView.class)), withText(text))).check(ViewAssertions.matches(isChecked()));
    }

    private Matcher<View> navigationMenuItem(final String text) {
        return new BaseMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText(NavigationMenuItemView.class.getName());
                description.appendText(" with text: ");
                description.appendText(text);
            }

            @Override
            public boolean matches(Object item) {
                if(!(item instanceof NavigationMenuItemView)) {
                     return false;
                }
                NavigationMenuItemView itemView = (NavigationMenuItemView) item;
                TextView viewById = (TextView) itemView.findViewById(R.id.account);
                //noinspection SimplifiableIfStatement for more readability
                if(viewById == null) {
                    return false;
                }
                return viewById.getText().equals(text);
            }

            @Override
            public void describeMismatch(Object item, Description mismatchDescription) {
                mismatchDescription.appendText("not match " + item.getClass().getSimpleName());
            }
        };
    }

    private static <E extends View & Checkable> Matcher<View> withCheckedState(final Matcher<Boolean> checkStateMatcher) {

        return new BoundedMatcher<View, E>(View.class, AppCompatCheckedTextView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("with checkbox state: ");
                checkStateMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(E checkable) {
                return checkStateMatcher.matches(checkable.isChecked());
            }
        };
    }
}
