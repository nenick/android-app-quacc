package de.nenick.robolectricpages;

import android.app.Activity;

import org.robolectric.Robolectric;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboBasePage<T extends Activity> {

    private Class<T> clazz;
    protected RoboBaseTest<T> roboBaseTest;

    public RoboBasePage(Class<T> clazz, RoboBaseTest<T> roboBaseTest) {
        this.clazz = clazz;
        this.roboBaseTest = roboBaseTest;
    }

    public void startRoboPage() {
        roboBaseTest.activityController = Robolectric.buildActivity(clazz);
        roboBaseTest.activity = roboBaseTest.activityController.setup().get();
    }


}
