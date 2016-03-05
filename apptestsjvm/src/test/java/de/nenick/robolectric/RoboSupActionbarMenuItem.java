package de.nenick.robolectric;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.robolectric.fakes.RoboMenuItem;

import de.nenick.robolectricpages.components.RoboActionbarMenuItem;

public class RoboSupActionbarMenuItem extends RoboActionbarMenuItem {

    private RoboSup<FragmentActivity, Fragment> roboSup;

    public RoboSupActionbarMenuItem(RoboSup roboSup, int resourceId, String fragmentTag) {
        super(roboSup, resourceId, fragmentTag);
        this.roboSup = roboSup;
    }

    public RoboSupActionbarMenuItem(RoboSup roboSup, int resourceId, int fragmentId) {
        super(roboSup, resourceId, fragmentId);
        this.roboSup = roboSup;
    }

    @Override
    public void click() {
        RoboMenuItem menuItem = new RoboMenuItem(resourceId);
        if (robo.activity.onOptionsItemSelected(menuItem)) {
            return;
        }
        // this does not work for fragments handled by support fragment manager
        Fragment fragmentByTag;
        if(fragmentTag != null) {
            fragmentByTag = roboSup.activity.getSupportFragmentManager().findFragmentByTag(fragmentTag);
        } else {
            fragmentByTag = roboSup.activity.getSupportFragmentManager().findFragmentById(fragmentId);
        }
        if (fragmentByTag != null && fragmentByTag.onOptionsItemSelected(menuItem)) {
            return;
        }
        throw new IllegalStateException("Actionbar menu item has no listener.");
    }
}
