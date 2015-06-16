package de.nenick.quacc.view.accounting_overview;

import android.os.Bundle;
import android.view.View;

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
import de.nenick.quacc.view.accounting_overview.adapter.AccountingCursorAdapter;
import de.nenick.quacc.view.accounting_overview.adapter.GroupingOptionAdapter;
import de.nenick.quacc.view.accounting_overview.filter.GetDateForRangeEndFunction;
import de.nenick.quacc.view.accounting_overview.filter.GetFilterRangesAsStringsFunction;
import de.nenick.quacc.view.accounting_overview.filter.GetDateForRangeStartFunction;
import de.nenick.quacc.common.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.common.mvp.BasePresenterFragment;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.view.accounting_create.CreateAccountingActivity_;
import de.nenick.quacc.view.accounting_overview.grouping.GetGroupingOptionsAsStringsFunction;
import de.nenick.quacc.view.accounting_overview.grouping.GroupingOption;
import de.nenick.quacc.view.i18n.MonthTranslator;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingAdapter;
import de.nenick.quacc.view.accounting_overview.filter.FilterRange;
import de.nenick.quacc.view.accounting_overview.adapter.FilterRangeAdapter;

@EFragment(R.layout.fragment_accounting_list)
public class AccountingListFragment extends BasePresenterFragment {

    @Bean
    AccountingListView view;

    @Bean
    AccountingAdapter accountingAdapter;

    @Bean
    AccountingCursorAdapter accountingCursorAdapter;

    @Bean
    MonthTranslator monthTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Bean
    AccountDb accountDb;

    @Bean
    FilterRangeAdapter filterRangeAdapter;

    @Bean
    GroupingOptionAdapter groupingOptionAdapter;

    @Bean
    GetDateForRangeEndFunction getDateForRangeEndFunction;

    @Bean
    GetDateForRangeStartFunction getDateForRangeStartFunction;

    @Bean
    GetFilterRangesAsStringsFunction getFilterRangesAsStringsFunction;

    @Bean
    GetGroupingOptionsAsStringsFunction getGroupingOptionsAsStringsFunction;

    @FragmentArg
    String account;
    private boolean extended;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        CreateAccountingActivity_.intent(this).start();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(savedInstanceState != null ) {
            extended = savedInstanceState.getBoolean("extended");
        }
    }

    @Override
    protected void onViewStart() {
        setHasOptionsMenu(true);

        groupingOptionAdapter.addAll(getGroupingOptionsAsStringsFunction.apply());
        view.setGroupingOptions(groupingOptionAdapter);
        onGroupingOptionChanged();

        filterRangeAdapter.addAll(getFilterRangesAsStringsFunction.apply());
        view.setFilterRanges(filterRangeAdapter);

        accountingAdapter.setAccount(account);
        accountingCursorAdapter.setAccount(account);

        resetFilter();

        AccountCursor accountCursor = accountDb.getAccountByName(account);
        accountCursor.moveToFirst();
        view.setAccountValue(parseValueFromIntegerFunction.apply(accountCursor.getInitialvalue()));
    }

    @Override
    protected void onViewResume() {
        view.showFilterVisibility(extended);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("extended", extended);
    }

    private void changeRangeFilterToCurrentMonth() {
        String year = QuAccDateUtil.currentYear();
        String month = monthTranslator.translate(QuAccDateUtil.currentMonth());

        // avoid not necessary reload events
        if(!view.getYear().equals(year)) {
            view.setYear(year);
        }

        // avoid not necessary reload events
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
            FilterRange filterRange = FilterRange.valueOf(filterRangeString);
            if(filterRange == FilterRange.free) {
                view.showFilterFreeRange();
            } else {
                view.hideFilterFreeRange();
            }
            DateTime startDate = getDateForRangeStartFunction.apply(filterRange, monthTranslator.translate(month), year);
            DateTime endDate = getDateForRangeEndFunction.apply(filterRange, startDate);
            accountingAdapter.changeFor(startDate, endDate);
            accountingCursorAdapter.changeFor(startDate, endDate);
        }
    }

    @ItemSelect(R.id.filterRange)
    protected void onFilterByRange(boolean selected) {
        onFilterChanged();
    }

    @ItemSelect(R.id.grouping)
    protected void onGroupingOption(boolean selected) {
        onGroupingOptionChanged();
    }

    protected void onGroupingOptionChanged() {
        String groupingString = view.getGroupingOption();
        if(GroupingOption.valueOf(groupingString) == GroupingOption.no_grouping) {
            view.setListAdapter(accountingAdapter);
        } else {
            view.setListAdapter(accountingCursorAdapter);
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

    @OptionsItem(R.id.filterToggle)
    protected void onToggleFilterView() {
        view.showFilterVisibility(extended = !extended);
        resetFilter();
    }

    private void resetFilter() {
        changeRangeFilterToCurrentMonth();
        view.setFilterRange(FilterRange.current_month.name());
        view.setGroupingOption(GroupingOption.categories.name());
        onFilterChanged();
        onGroupingOptionChanged();
    }

    private boolean isViewFullInitialised(String month, String year, String filterRange) {
        return !(month.isEmpty() || year.isEmpty() || Strings.isNullOrEmpty(filterRange));
    }
}
