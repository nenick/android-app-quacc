package de.nenick.quacc.database.testsupport;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

public class CauseMatcher extends TypeSafeMatcher<Throwable> {

    public static CauseMatcher containsMessage(String expectedMessagePart) {
        return new CauseMatcher(expectedMessagePart);
    }

    private final String expectedMessagePart;

    public CauseMatcher(String expectedMessagePart) {
        this.expectedMessagePart = expectedMessagePart;
    }

    @Override
    protected boolean matchesSafely(Throwable item) {
        return item.getMessage().contains(expectedMessagePart);
    }

    boolean isFirstMessage = true;

    @Override
    public void describeTo(Description description) {
        if (isFirstMessage) {
            description.appendText("containing ").appendValue(expectedMessagePart);
            isFirstMessage = false;
        } else {
            description.appendText("does not contain expected part").appendText("\n");
        }
    }
}
