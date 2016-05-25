package de.nenick.quacc.view.bookingentries;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.joda.time.DateTime;

import de.nenick.quacc.R;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.MonthTranslator;

@EFragment
@OptionsMenu(R.menu.date_selector)
public class BookingEntriesFragment extends Fragment implements BookingEntriesView.ViewCallback {

    @Bean
    protected BookingEntriesView view;

    @Bean
    protected BookingEntriesListAdapter adapter;

    @Bean
    protected QuAccDateUtil quAccDateUtil;

    @Bean
    protected MonthTranslator monthTranslator;

    @InstanceState
    protected boolean switchDateExpanded;

    @InstanceState
    protected DateTime selectedDate;

    private long account;

    @AfterInject
    protected void onAfterInjectBeans() {
        if (selectedDate == null) {
            // else it was restored from instance state
            selectedDate = quAccDateUtil.today();
        }
        view.setViewCallback(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return view.root();
    }

    @AfterViews
    void onAfterViewsCreated() {
        if (switchDateExpanded) {
            view.toggleDateSelector();
        }
        view.setAdapter(adapter);
        updateSelectedDateText();
    }

    public void setAccount(long account) {
        this.account = account;
        updateSelectedDateText();
        updateBookingList();
    }

    @OptionsItem(R.id.dateSelectorToggle)
    public void onClickDateSelectorToggle() {
        switchDateExpanded = !switchDateExpanded;
        view.toggleDateSelector();
    }

    @Override
    public void onClickMonthUp() {
        selectedDate = selectedDate.plusMonths(1);
        updateSelectedDateText();
        updateBookingList();
    }

    @Override
    public void onClickMonthDown() {
        selectedDate = selectedDate.minusMonths(1);
        updateSelectedDateText();
        updateBookingList();
    }

    @Override
    public void onClickYearUp() {
        selectedDate = selectedDate.plusYears(1);
        updateSelectedDateText();
        updateBookingList();
    }

    @Override
    public void onClickYearDown() {
        selectedDate = selectedDate.minusYears(1);
        updateSelectedDateText();
        updateBookingList();
    }

    private void updateSelectedDateText() {
        int month = selectedDate.getMonthOfYear();
        view.setMonth(monthTranslator.translate(month));

        int year = selectedDate.getYear();
        view.setYear(String.valueOf(year));
    }

    private void updateBookingList() {
        adapter.update(account, quAccDateUtil.firstDayOfMonth(selectedDate), quAccDateUtil.lastDayOfMonth(selectedDate));
    }
}
