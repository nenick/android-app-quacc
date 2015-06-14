package de.nenick.robolectricpages.components;

import android.app.Fragment;

import org.robolectric.fakes.RoboMenuItem;

import de.nenick.robolectricpages.Robo;

public class RoboActionbarMenuItem extends RoboBaseComponent {

    protected final int resourceId;
    protected final int fragmentId;
    protected final String fragmentTag;

    public RoboActionbarMenuItem(Robo robo, int resourceId, String fragmentTag) {
        super(robo);
        this.resourceId = resourceId;
        this.fragmentTag = fragmentTag;
        this.fragmentId = 0;
    }

    public RoboActionbarMenuItem(Robo robo, int resourceId, int fragmentId) {
        super(robo);
        this.resourceId = resourceId;
        this.fragmentTag = null;
        this.fragmentId = fragmentId;
    }

    public void click() {
        RoboMenuItem menuItem = new RoboMenuItem(resourceId);
        if (robo.activity.onOptionsItemSelected(menuItem)) {
            return;
        }
        // this does not work for fragments handled by support fragment manager
        Fragment fragmentByTag;
        if(fragmentTag != null) {
            fragmentByTag = robo.activity.getFragmentManager().findFragmentByTag(fragmentTag);
        } else {
            fragmentByTag = robo.activity.getFragmentManager().findFragmentById(fragmentId);
        }

        if (fragmentByTag != null && fragmentByTag.onOptionsItemSelected(menuItem)) {
            return;
        }
        throw new IllegalStateException("Actionbar menu item has no listener.");
    }
}
