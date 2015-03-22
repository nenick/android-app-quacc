package de.nenick.robolectricpages.components;

import android.widget.ImageButton;

import org.robolectric.Shadows;

import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.utils.RoboClickUtil;

public class RoboImageButton extends RoboBaseComponent {

    private ImageButton button;

    public RoboImageButton(Robo robo, int resourceId) {
        super(robo);
        button = (ImageButton) robo.activity.findViewById(resourceId);
    }

    public void click() {
        RoboClickUtil.click(button);
    }

    public int getDrawableResId() {
        return Shadows.shadowOf(button.getDrawable()).getCreatedFromResId();
    }
}
