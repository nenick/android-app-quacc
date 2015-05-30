package de.nenick.quacc.accounting.create;

import de.nenick.quacc.R;
import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboSpinnerEntry;

public class RoboCategorySpinner extends RoboSpinner {
    public RoboCategorySpinner(Robo robo, int resourceId) {
        super(robo, resourceId);
    }

    @Override
    public RoboSpinnerEntry entry(String text) {
        return super.entry(text, R.id.text1);
    }
}
