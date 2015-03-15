package de.nenick.robolectricpages.utils;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class RoboClickUtil {

    public static void click(TextView view) {
        if(!view.performClick()) {
            throw new IllegalStateException("View has no click listener.");
        }
    }

    public static void click(Button view) {
        if(!view.performClick()) {
            throw new IllegalStateException("View has no click listener.");
        }
    }

    public static void click(ImageButton view) {
        if(!view.performClick()) {
            throw new IllegalStateException("View has no click listener.");
        }
    }
}
