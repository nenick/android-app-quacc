package de.nenick.quacc.accountinglist;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.accountinglist.list.AccountingListAdapter;
import de.nenick.quacc.accounting.create.CreateAccountingActivity_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EFragment(R.layout.fragment_accounting_list)
public class AccountingListFragment extends Fragment {

    @ViewById(R.id.listView)
    ListView accountingList;

    @Bean
    AccountingListAdapter accountingListAdapter;

    @Bean
    AccountingListPresenter presenter;

    @AfterViews
    protected void onAfterViews() {
        accountingList.setAdapter(accountingListAdapter);
        presenter.onViewCreated(this);
    }

    public void showAccountingList(AccountingCursor accountingCursor) {
        accountingListAdapter.swapCursor(accountingCursor);
    }

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        CreateAccountingActivity_.intent(this).start();
    }
}
