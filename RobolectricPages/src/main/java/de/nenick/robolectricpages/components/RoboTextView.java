package de.nenick.robolectricpages.components;

import android.widget.TextView;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboTextView extends RoboBaseComponent {

    protected TextView textView;

    public RoboTextView(RoboBaseTest roboBaseTest, int resourceId) {
        super(roboBaseTest);
        textView = (TextView) roboBaseTest.activity.findViewById(resourceId);
    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void click() {
        if(!textView.performClick()) {
            throw new IllegalStateException("TextView has no click listener.");
        }
    }
}
