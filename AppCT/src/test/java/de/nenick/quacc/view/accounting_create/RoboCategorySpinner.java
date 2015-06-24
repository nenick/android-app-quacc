package de.nenick.quacc.view.accounting_create;

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
