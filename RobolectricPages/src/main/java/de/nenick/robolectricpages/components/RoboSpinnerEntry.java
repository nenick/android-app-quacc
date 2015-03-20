package de.nenick.robolectricpages.components;

import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.utils.RoboTextViewUtil;

public class RoboSpinnerEntry extends RoboBaseComponent {

    private final Spinner spinner;
    private final int position;
    private View spinnerChildView;

    public RoboSpinnerEntry(Robo robo, Spinner spinner, int position) {
        super(robo);
        this.spinner = spinner;
        this.position = position;
        this.spinnerChildView = spinner.getAdapter().getView(position, null, spinner);
        if(spinnerChildView == null) throw new IllegalArgumentException("spinner entry is null");
    }

    public String getText() {
        if(!isSimpleTextView()) {
            throw new IllegalStateException("The spinner entry is not a simple textView. You should use getText(int resourceId) instead.");
        }
        return ((TextView) spinnerChildView).getText().toString();
    }

    public CharSequence getText(int resourceId) {
        return RoboTextViewUtil.getTextFromView(robo, resourceId);
    }

    private boolean isSimpleTextView() {
        return spinnerChildView instanceof TextView;
    }

    public void select() {
        spinner.setSelection(position);
    }
}
