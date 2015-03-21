package de.nenick.quacc.accountinglist;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.R;
import de.nenick.quacc.main.MainActivity;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboButton;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboListView;

public class RoboAccountingListPage extends RoboSupPage<AccountingListActivity_, AccountingListFragment_> {

    public RoboAccountingListPage(RoboSup<AccountingListActivity_, AccountingListFragment_> robo) {
        super(robo, MainActivity.TAG_FRAGMENT);
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
