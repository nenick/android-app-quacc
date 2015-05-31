package de.nenick.quacc.accounting.list;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.CreateAccountingActivity_;
import de.nenick.quacc.common.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.i18n.MonthTranslator;

@EFragment(R.layout.fragment_accounting_list)
@OptionsMenu(R.menu.menu_accounting_list)
public class AccountingListFragment extends BasePresenterFragment {

    @Bean
    AccountingListView view;

    @Bean
    AccountingAdapter accountingAdapter;

    @Bean
    MonthTranslator monthTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Bean
    AccountDb accountDb;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        CreateAccountingActivity_.intent(this).start();
    }

    @Override
    protected void onViewStart() {
        view.setListAdapter(accountingAdapter);
        view.setYear(QuAccDateUtil.currentYear());
        view.setMonth(monthTranslator.translate(QuAccDateUtil.currentMonth()));
        AccountCursor bar = accountDb.getAccountByName("Bar");
        bar.moveToFirst();
        view.setAccountValue(parseValueFromIntegerFunction.apply(bar.getInitialvalue()));
    }

    @AfterTextChange({R.id.month, R.id.year})
    protected void onFilterChanged() {
        String month = view.getMonth();
        String year = view.getYear();
        if(isViewFullInitialised(month, year)) {
            accountingAdapter.updateFor(monthTranslator.translate(month), year);
        }
    }

    @Click(R.id.monthUp)
    public void onMonthUp() {
        view.setMonth(monthTranslator.translate(QuAccDateUtil.monthAfterOf(monthTranslator.translate(view.getMonth()))));
    }

    @Click(R.id.monthDown)
    public void onMonthDown() {
        view.setMonth(monthTranslator.translate(QuAccDateUtil.monthBeforeOf(monthTranslator.translate(view.getMonth()))));
    }

    @Click(R.id.yearUp)
    public void onYearUp() {
        view.setYear(String.valueOf(Integer.parseInt(view.getYear()) + 1));
    }

    @Click(R.id.yearDown)
    public void onYearDown() {
        view.setYear(String.valueOf(Integer.parseInt(view.getYear()) - 1));
    }

    private boolean isViewFullInitialised(String month, String year) {
        return !(month.isEmpty() || year.isEmpty());
    }
}
