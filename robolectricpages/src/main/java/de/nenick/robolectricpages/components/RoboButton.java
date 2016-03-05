package de.nenick.robolectricpages.components;

import android.widget.Button;

import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.utils.RoboClickUtil;

public class RoboButton extends RoboBaseComponent {

    private Button button;

    public RoboButton(Robo robo, int resourceId) {
        super(robo);
        button = (Button) robo.activity.findViewById(resourceId);
    }

    public void click() {
        RoboClickUtil.click(button);
    }
}
