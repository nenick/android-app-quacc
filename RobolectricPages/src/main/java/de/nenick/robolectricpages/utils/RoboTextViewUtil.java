package de.nenick.robolectricpages.utils;

import android.view.View;
import android.widget.TextView;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboTextViewUtil {
    public static String getTextFromView(RoboBaseTest roboBaseTest, int resourceId) {
        View view = roboBaseTest.activity.findViewById(resourceId);
        if(view instanceof TextView) {
            return ((TextView)view).getText().toString();
        }
        throw new IllegalStateException(view.getClass() + " is not handled yet.");
    }
}
