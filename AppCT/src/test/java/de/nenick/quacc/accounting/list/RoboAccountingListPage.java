package de.nenick.quacc.accounting.list;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListPage extends RoboSupPage<AccountingListActivity_, AccountingListFragment_> {

    public RoboAccountingListPage(RoboSup<AccountingListActivity_, AccountingListFragment_> robo) {
        super(robo, AccountingListActivity.TAG_FRAGMENT);
    }

    public static Intent Intent() {
        return AccountingListActivity_.intent(RuntimeEnvironment.application).get();
    }

    public RoboListView list() {
        return new RoboListView(robo, R.id.listView);
    }

    public RoboImageButton addAccountingButton() {
        return new RoboImageButton(robo, R.id.btn_add_accounting);
    }
}
