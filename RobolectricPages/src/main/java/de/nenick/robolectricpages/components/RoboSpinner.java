package de.nenick.robolectricpages.components;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import de.nenick.robolectricpages.Robo;

public class RoboSpinner extends RoboBaseComponent {

    protected Spinner spinner;

    public RoboSpinner(Robo robo, int resourceId) {
        super(robo);
        spinner = (Spinner) robo.activity.findViewById(resourceId);
    }

    public List<RoboSpinnerEntry> entries() {
        List<RoboSpinnerEntry> spinnerEntries = new ArrayList<>();
        for (int position = 0; position < spinner.getCount(); position++) {
            spinnerEntries.add(new RoboSpinnerEntry(robo, spinner, position));
        }
        return spinnerEntries;
    }

    public RoboSpinnerEntry selectedEntry() {
        return new RoboSpinnerEntry(robo, spinner, spinner.getSelectedItemPosition());
    }
}
