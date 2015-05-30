package de.nenick.quacc.accounting.list;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.common.util.QuAccDateUtil;

@EFragment(R.layout.fragment_accounting_list)
@OptionsMenu(R.menu.menu_accounting_list)
public class AccountingListFragment extends BasePresenterFragment {

    @Bean
    AccountingListView view;

    @Bean
    AccountingAdapter accountingAdapter;


    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Override
    protected void onViewStart() {
        view.setListAdapter(accountingAdapter);
        view.setYear(QuAccDateUtil.currentYear());
        view.setMonth(QuAccDateUtil.currentMonth());
    }

    @AfterTextChange({R.id.month, R.id.year})
    protected void onFilterChanged() {
        String month = view.getMonth();
        String year = view.getYear();
        if(isViewFullInitialised(month, year)) {
            accountingAdapter.updateFor(month, year);
        }
    }

    private boolean isViewFullInitialised(String month, String year) {
        return !(month.isEmpty() || year.isEmpty());
    }
}
