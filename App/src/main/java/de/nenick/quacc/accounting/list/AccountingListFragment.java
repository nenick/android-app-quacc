package de.nenick.quacc.accounting.list;

import com.google.common.base.Strings;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.joda.time.DateTime;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.CreateAccountingActivity_;
import de.nenick.quacc.accounting.list.functions.GetEndDateForRangeFunction;
import de.nenick.quacc.accounting.list.functions.GetFilterRangesFunction;
import de.nenick.quacc.common.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.i18n.MonthTranslator;

@EFragment(R.layout.fragment_accounting_list)
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

    @Bean
    FilterRangeAdapter filterRangeAdapter;

    @Bean
    GetEndDateForRangeFunction getEndDateForRangeFunction;

    @Bean
    GetFilterRangesFunction getFilterRangesFunction;

    @FragmentArg
    String account;

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
        setHasOptionsMenu(true);

        filterRangeAdapter.addAll(getFilterRangesFunction.apply());
        view.setFilterRanges(filterRangeAdapter);

        accountingAdapter.setAccount(account);
        view.setListAdapter(accountingAdapter);

        changeRangeFilterToCurrentMonth();

        AccountCursor bar = accountDb.getAccountByName("Bar");
        bar.moveToFirst();
        view.setAccountValue(parseValueFromIntegerFunction.apply(bar.getInitialvalue()));
    }

    private void changeRangeFilterToCurrentMonth() {
        String year = QuAccDateUtil.currentYear();
        if(!view.getYear().equals(year)) {
            view.setYear(year);
        }
        String month = monthTranslator.translate(QuAccDateUtil.currentMonth());
        if(!view.getMonth().equals(month)) {
            view.setMonth(month);
        }
    }

    @AfterTextChange({R.id.month, R.id.year})
    protected void onFilterChanged() {
        String month = view.getMonth();
        String year = view.getYear();
        String filterRangeString = view.getFilterRange();

        if (isViewFullInitialised(month, year, filterRangeString)) {
            DateTime firstDayOfSelectedMonth = QuAccDateUtil.toDateTime(1, monthTranslator.translate(month), year);
            FilterRange filterRange = FilterRange.valueOf(filterRangeString);
            if(filterRange == FilterRange.free) {
                view.showFilterFreeRange();
            } else {
                view.hideFilterFreeRange();
                changeRangeFilterToCurrentMonth();
            }
            DateTime endDate = getEndDateForRangeFunction.apply(filterRange, firstDayOfSelectedMonth);
            accountingAdapter.changeFor(firstDayOfSelectedMonth, endDate);
        }
    }

    @ItemSelect(R.id.filterRange)
    protected void onFilterByRange(boolean selected) {
        onFilterChanged();
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

    @OptionsItem(R.id.filterToggle)
    protected void onToggleFilterView() {
        view.toggleFilterVisibility();
    }

    private boolean isViewFullInitialised(String month, String year, String filterRange) {
        return !(month.isEmpty() || year.isEmpty() || Strings.isNullOrEmpty(filterRange));
    }
}
