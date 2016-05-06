package de.nenick.quacc.view.accounting_overview;

import android.content.Intent;

import org.robolectric.RuntimeEnvironment;

import de.nenick.quacc.R;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboListView;
import de.nenick.robolectricpages.components.RoboTextView;

public class RoboAccountingListPage extends RoboSupPage<AccountingListActivity_, AccountingListFragment_> {

    public RoboAccountingListPage() {
        super(new RoboSup<AccountingListActivity_, AccountingListFragment_>(), AccountingListActivity.TAG_FRAGMENT);
    }

    @Override
    public void startPage() {
        super.startPage();
        // At first start the drawer is open per default. This behaviour is android default.
        drawer().close();
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

    public RoboAccountingListDrawer drawer() {
        return new RoboAccountingListDrawer(robo);
    }
}
