package de.nenick.robolectricpages.components;

import android.widget.TextView;

import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.utils.RoboClickUtil;

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
        RoboClickUtil.click(textView);
    }
}
