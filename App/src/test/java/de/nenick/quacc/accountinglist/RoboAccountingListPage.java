package de.nenick.quacc.accountinglist;

import de.nenick.quacc.MainActivity;
import de.nenick.quacc.MainActivity_;
import de.nenick.quacc.MainFragment_;
import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectric.RoboSupTest;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListPage extends RoboSupPage<MainActivity_, MainFragment_> {

    public RoboAccountingListPage(RoboSupTest<MainActivity_, MainFragment_> roboSupTest) {
        super(MainActivity_.class, roboSupTest, MainActivity.TAG_FRAGMENT);
    }

    public RoboListView list() {
        return new RoboListView(robo, R.id.listView);
    }
}
