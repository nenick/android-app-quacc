package de.nenick.robolectricpages;

import android.app.Activity;

import org.robolectric.util.ActivityController;

public class RoboBaseTest<T extends Activity> {

    public T activity;
    public ActivityController<T> activityController;
}
