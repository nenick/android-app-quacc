package de.nenick.robolectricpages.components;

import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboSpinner extends RoboBaseComponent {

    protected Spinner spinner;

    public RoboSpinner(RoboBaseTest roboBaseTest, int resourceId) {
        super(roboBaseTest);
        spinner = (Spinner) roboBaseTest.activity.findViewById(resourceId);
    }

    public List<RoboSpinnerEntry> entries() {
        List<RoboSpinnerEntry> spinnerEntries = new ArrayList<>();
        for (int position = 0; position < spinner.getCount(); position++) {
            spinnerEntries.add(new RoboSpinnerEntry(roboBaseTest, spinner, position));
        }
        return spinnerEntries;
    }

    public RoboSpinnerEntry selectedEntry() {
        return new RoboSpinnerEntry(roboBaseTest, spinner, spinner.getSelectedItemPosition());
    }
}
