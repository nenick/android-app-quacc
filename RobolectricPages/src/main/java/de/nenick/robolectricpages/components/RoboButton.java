package de.nenick.robolectricpages.components;

import android.widget.Button;

import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.utils.RoboClickUtil;

public class RoboButton extends RoboBaseComponent {

    private Button button;

    public RoboButton(RoboBaseTest roboBaseTest, int resourceId) {
        super(roboBaseTest);
        button = (Button) roboBaseTest.activity.findViewById(resourceId);
    }

    public void click() {
        RoboClickUtil.click(button);
    }
}
