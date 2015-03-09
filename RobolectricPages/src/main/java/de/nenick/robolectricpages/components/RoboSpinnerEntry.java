package de.nenick.robolectricpages.components;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboSpinnerEntry extends RoboBaseComponent {

    private final Spinner spinner;
    private final int position;
    private View spinnerChildView;

    public RoboSpinnerEntry(RoboBaseTest roboBaseTest, Spinner spinner, int position, View spinnerChildView) {
        super(roboBaseTest);
        this.spinner = spinner;
        this.position = position;
        this.spinnerChildView = spinnerChildView;
        if(spinnerChildView == null) throw new IllegalArgumentException("spinner entry is null");
    }

    public String getText() {
        if(!isSimpleTextView()) {
            throw new IllegalStateException("The spinner entry is no a simple textView. You should use getText(int resourceId) instead.");
        }
        return ((TextView) spinnerChildView).getText().toString();
    }

    public CharSequence getText(int resourceId) {
        TextView textView = (TextView) spinnerChildView.findViewById(resourceId);
        if(textView == null) throw new IllegalStateException("View does not contains the expected ui element." + (isSimpleTextView()?" Detected a simple TextView so try instead getText() without specific resourceId.":""));
        return textView.getText();
    }

    private boolean isSimpleTextView() {
        return spinnerChildView instanceof TextView;
    }

    public void select() {
        spinner.setSelection(position);
    }
}
