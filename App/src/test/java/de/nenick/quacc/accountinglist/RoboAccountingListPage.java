package de.nenick.quacc.accountinglist;

import de.nenick.quacc.MainActivity;
import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListPage extends RoboSupPage<AccountingListActivity_, AccountingListFragment_> {

    public RoboAccountingListPage(RoboSup<AccountingListActivity_, AccountingListFragment_> robo) {
        super(robo, MainActivity.TAG_FRAGMENT);
    }

    public RoboListView list() {
        return new RoboListView(robo, R.id.listView);
    }
}
