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


    public RoboSpinnerEntry entry(String text, int resourceId) {
        for (RoboSpinnerEntry entry : entries()) {
            if (entry.getText(resourceId).equals(text)) {
                return entry;
            }
        }

        throw new IllegalStateException("No entry found with text " + text);
    }

    public RoboSpinnerEntry entry(String text) {
        for (RoboSpinnerEntry entry : entries()) {
            if (entry.getText().equals(text)) {
                return entry;
            }
        }

        throw new IllegalStateException("No entry found with text " + text);
    }

    public RoboSpinnerEntry selectedEntry() {
        return new RoboSpinnerEntry(robo, spinner, spinner.getSelectedItemPosition());
    }
}
