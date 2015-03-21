package de.nenick.robolectricpages;

import android.app.Activity;
import android.content.Intent;

import org.robolectric.Robolectric;
import org.robolectric.Shadows;

import java.lang.reflect.ParameterizedType;

public class RoboBasePage<T extends Activity, B extends Robo<T>> {

    protected B robo;

    public RoboBasePage(B robo) {
        this.robo = robo;
    }

    public void startPage() {
        robo.activityController = Robolectric.buildActivity(getActivityClass());
        robo.activity = robo.activityController.setup().get();
    }

    protected void createPage() {
        robo.activityController = Robolectric.buildActivity(getActivityClass());
        robo.activity = robo.activityController.create().get();
    }

    protected void startCreatedPage() {
        robo.activityController.start().postCreate(null).resume().visible();
    }

    public Intent nextStartedPage() {
        return Shadows.shadowOf(robo.activity).getNextStartedActivity();
    }


    private Class<T> getActivityClass() {
        ParameterizedType genericSuperclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
}
