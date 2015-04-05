package de.nenick.robolectricpages.components;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import de.nenick.robolectricpages.Robo;
import de.nenick.robolectricpages.utils.RoboTextViewUtil;

public class RoboListViewEntry extends RoboBaseComponent {

    private final ListView listView;
    private final int position;
    private View listChildView;

    public RoboListViewEntry(Robo robo, ListView listView, int position) {
        super(robo);
        this.listView = listView;
        this.position = position;
        this.listChildView = listView.getChildAt(position);
        if (listChildView == null) throw new IllegalArgumentException("listView entry is null");
    }

    public String getText() {
        if (!isSimpleTextView()) {
            throw new IllegalStateException("The listView entry is not a simple textView. You should use getText(int resourceId) instead.");
        }
        return ((TextView) listChildView).getText().toString();
    }

    public CharSequence getText(int resourceId) {
        return RoboTextViewUtil.getTextFromView(listChildView, resourceId);
    }

    private boolean isSimpleTextView() {
        return listChildView instanceof TextView;
    }

    public void select() {
        listView.setSelection(position);
    }
}
