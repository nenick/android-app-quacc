package de.nenick.robolectricpages;

import android.app.Activity;

import org.robolectric.util.ActivityController;

public class Robo<T extends Activity> {

    public T activity;
    public ActivityController<T> activityController;
}
