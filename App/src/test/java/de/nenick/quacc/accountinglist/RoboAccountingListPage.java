package de.nenick.quacc.accountinglist;

import de.nenick.quacc.MainActivity;
import de.nenick.quacc.MainActivity_;
import de.nenick.quacc.MainFragment_;
import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListPage extends RoboSupPage<MainActivity_, MainFragment_> {

    public RoboAccountingListPage(RoboSup<MainActivity_, MainFragment_> robo) {
        super(MainActivity_.class, robo, MainActivity.TAG_FRAGMENT);
    }

    public RoboListView list() {
        return new RoboListView(robo, R.id.listView);
    }
}
