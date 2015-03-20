package de.nenick.robolectricpages.components;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import de.nenick.robolectricpages.RoboBaseTest;
import de.nenick.robolectricpages.utils.RoboTextViewUtil;

public class RoboListViewEntry extends RoboBaseComponent {

    private final ListView listView;
    private final int position;
    private View spinnerChildView;

    public RoboListViewEntry(RoboBaseTest roboBaseTest, ListView listView, int position) {
        super(roboBaseTest);
        this.listView = listView;
        this.position = position;
        this.spinnerChildView = listView.getChildAt(position);
        if(spinnerChildView == null) throw new IllegalArgumentException("listView entry is null");
    }

    public String getText() {
        if(!isSimpleTextView()) {
            throw new IllegalStateException("The listView entry is not a simple textView. You should use getText(int resourceId) instead.");
        }
        return ((TextView) spinnerChildView).getText().toString();
    }

    public CharSequence getText(int resourceId) {
        return RoboTextViewUtil.getTextFromView(roboBaseTest, resourceId);
    }

    private boolean isSimpleTextView() {
        return spinnerChildView instanceof TextView;
    }

    public void select() {
        listView.setSelection(position);
    }
}
