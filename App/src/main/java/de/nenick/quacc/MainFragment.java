package de.nenick.quacc;

import android.support.v4.app.Fragment;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.accountinglist.AccountingListAdapter;
import de.nenick.quacc.addaccounting.AddAccountingActivity_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @ViewById(R.id.listView)
    ListView listView;

    @Bean
    AccountingListAdapter accountingListAdapter;

    public static MainFragment build() {
        return MainFragment_.builder().build();
    }

    @AfterViews
    public void afterViews() {
        listView.setAdapter(accountingListAdapter);
    }

    @Click(R.id.button)
    protected void onStartAddAccountProcess() {
        AddAccountingActivity_.intent(this).start();
    }
}
