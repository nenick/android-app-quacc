package de.nenick.robolectricpages;

import android.app.Activity;

import org.robolectric.Robolectric;

public class RoboBasePage<T extends Activity, B extends RoboBaseTest<T>> {

    private Class<T> clazz;
    protected B robo;

    public RoboBasePage(Class<T> clazz, B robo) {
        this.clazz = clazz;
        this.robo = robo;
    }

    public void startPage() {
        robo.activityController = Robolectric.buildActivity(clazz);
        robo.activity = robo.activityController.setup().get();
    }

    protected void createPage() {
        robo.activityController = Robolectric.buildActivity(clazz);
        robo.activity = robo.activityController.create().get();
    }

    protected void startCreatedPage() {
        robo.activityController.start().postCreate(null).resume().visible();
    }
}
