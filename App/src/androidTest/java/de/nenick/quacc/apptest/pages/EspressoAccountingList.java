package de.nenick.quacc.apptest.pages;

import android.support.test.espresso.matcher.BoundedMatcher;

import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.Map;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.internal.util.Checks.checkNotNull;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.core.IsEqual.equalTo;

public class EspressoAccountingList {
    public void doNotContain(String text) {

    }

    public void doesContain(String text) {
        /*onData(withItemContent(text))
                .onChildView(withId(R.id.list_item))
                .check(matches(isDisplayed()));
                */
    }

    public static Matcher<Object> withItemContent(String text) {
        checkNotNull(text);
        final Matcher<String> itemTextMatcher = equalTo(text);
        return new BoundedMatcher<Object, Map>(Map.class) {
            @Override
            public boolean matchesSafely(Map map) {
                return hasEntry(equalTo("description"), itemTextMatcher).matches(map);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with item content: ");
                itemTextMatcher.describeTo(description);
            }
        };
    }
}
