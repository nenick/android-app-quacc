package de.nenick.quacc.accounting.list;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.R;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboListView;
import de.nenick.robolectricpages.components.RoboTextView;

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

    public RoboTextView filterMonth() {
        return new RoboTextView(robo, R.id.month);
    }

    public RoboTextView filterMonthUp() {
        return new RoboTextView(robo, R.id.monthUp);
    }

    public RoboTextView filterMonthDown() {
        return new RoboTextView(robo, R.id.monthDown);
    }

    public RoboTextView filterYear() {
        return new RoboTextView(robo, R.id.year);
    }

    public RoboTextView filterYearUp() {
        return new RoboTextView(robo, R.id.yearUp);
    }

    public RoboTextView filterYearDown() {
        return new RoboTextView(robo, R.id.yearDown);
    }

    public RoboAccountingListActionbar actionbar() {
        return new RoboAccountingListActionbar(robo);
    }
}
