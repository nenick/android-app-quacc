package de.nenick.robolectricpages.components;

import android.widget.TextView;

import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.utils.RoboClickUtil;

public class RoboTextView extends RoboBaseComponent {

    protected TextView textView;

    public RoboTextView(Robo robo, int resourceId) {
        super(robo);
        textView = (TextView) robo.activity.findViewById(resourceId);
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
