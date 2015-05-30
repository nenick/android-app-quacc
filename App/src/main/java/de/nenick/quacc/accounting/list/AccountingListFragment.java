package de.nenick.quacc.accounting.list;

import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;

@EFragment(R.layout.fragment_accounting_list)
@OptionsMenu(R.menu.menu_accounting_list)
public class AccountingListFragment extends Fragment {

    @Bean
    AccountingListView view;

    @Bean
    AccountingAdapter accountingAdapter;

    @AfterViews
    protected void onAfterViews() {
        view.setListAdapter(accountingAdapter);
    }
}
