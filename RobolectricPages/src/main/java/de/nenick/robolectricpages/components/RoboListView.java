package de.nenick.robolectricpages.components;

import android.widget.ListView;

import org.robolectric.Shadows;

import java.util.ArrayList;
import java.util.List;

import de.nenick.robolectricpages.RoboBaseTest;

public class RoboListView extends RoboBaseComponent {

    ListView listView;

    public RoboListView(RoboBaseTest roboBaseTest, int resourceId) {
        super(roboBaseTest);
        listView = (ListView) roboBaseTest.activity.findViewById(resourceId);
        Shadows.shadowOf(listView).populateItems();
    }

    public int count() {
        return listView.getCount();
    }

    public List<RoboListViewEntry> entries() {
        List<RoboListViewEntry> list = new ArrayList<>();
        for (int i = 0; i < listView.getChildCount(); i++) {
            list.add(new RoboListViewEntry(roboBaseTest, listView, i));
        }
        return list;
    }
}
