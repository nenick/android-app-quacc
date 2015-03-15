package de.nenick.robolectricpages.components;

import android.widget.Button;
import android.widget.ImageButton;

import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.utils.RoboClickUtil;

public class RoboImageButton extends RoboBaseComponent {

    private ImageButton button;

    public RoboImageButton(RoboBaseTest roboBaseTest, int resourceId) {
        super(roboBaseTest);
        button = (ImageButton) roboBaseTest.activity.findViewById(resourceId);
    }

    public void click() {
        RoboClickUtil.click(button);
    }
}
