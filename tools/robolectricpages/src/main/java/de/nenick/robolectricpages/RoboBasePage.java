package de.nenick.robolectricpages;

import android.app.Activity;
import android.content.Intent;

import org.robolectric.Robolectric;
import org.robolectric.Shadows;

import java.lang.reflect.ParameterizedType;

public class RoboBasePage<T extends Activity, B extends Robo<T>> {

    public B robo;

    public RoboBasePage(B robo) {
        this.robo = robo;
    }

    public void startPage() {
        robo.activityController = Robolectric.buildActivity(getActivityClass());
        robo.activity = robo.activityController.setup().get();
    }

    public void finishPage() {
        robo.activityController.destroy();
        robo.activity = null;
    }

    public void startPage(Intent intent) {
        robo.activityController = Robolectric.buildActivity(getActivityClass());
        robo.activity = robo.activityController.withIntent(intent).setup().get();
    }

    protected void createPage() {
        robo.activityController = Robolectric.buildActivity(getActivityClass());
        robo.activity = robo.activityController.create().start().get();
    }

    protected void startCreatedPage() {
        robo.activityController.postCreate(null).resume().visible();
    }

    public Intent nextStartedPage() {
        return Shadows.shadowOf(robo.activity).getNextStartedActivity();
    }


    @SuppressWarnings("unchecked")
    private Class<T> getActivityClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}
