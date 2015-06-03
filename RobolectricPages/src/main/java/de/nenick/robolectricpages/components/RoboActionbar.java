package de.nenick.robolectricpages.components;


import de.nenick.robolectricpages.Robo;

public class RoboActionbar extends RoboBaseComponent {

    public RoboActionbar(Robo robo) {
        super(robo);
    }

    public RoboActionbarMenuItem getMenuItem(int resourceId, String fragmentTag) {
        return new RoboActionbarMenuItem(robo, resourceId, fragmentTag);
    }

    public String title() {
        return robo.activity.getTitle().toString();
    }
}
