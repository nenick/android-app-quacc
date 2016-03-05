package de.nenick.quacc.view.accounting_overview;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.common.base.Strings;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.ItemSelect;
import org.androidannotations.annotations.OptionsItem;
import org.joda.time.DateTime;

import de.nenick.quacc.R;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.MonthTranslator;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.speechrecognition.hotword.HotwordListener;
import de.nenick.quacc.speechrecognition.hotword.QuAccHotwordRecognizer;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_edit.EditAccountingActivity_;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingPlainAdapter;
import de.nenick.quacc.view.accounting_overview.adapter.AccountingTreeAdapter;
import de.nenick.quacc.view.accounting_overview.adapter.FilterRangeAdapter;
import de.nenick.quacc.view.accounting_overview.adapter.GroupingOptionAdapter;
import de.nenick.quacc.view.accounting_overview.filter.FilterRange;
import de.nenick.quacc.view.accounting_overview.filter.GetDateForRangeEndFunction;
import de.nenick.quacc.view.accounting_overview.filter.GetDateForRangeStartFunction;
import de.nenick.quacc.view.accounting_overview.filter.GetFilterRangesAsStringsFunction;
import de.nenick.quacc.view.accounting_overview.grouping.GetGroupingOptionsAsStringsFunction;
import de.nenick.quacc.view.accounting_overview.grouping.GroupingOption;
import de.nenick.quacc.view.mvp.BasePresenterFragment;
import de.nenick.quacc.view.mvp.BaseView;

@EFragment(R.layout.fragment_accounting_list)
public class AccountingListFragment extends BasePresenterFragment {

    @Bean
    AccountingListView view;

    @Bean
    AccountingPlainAdapter accountingPlainAdapter;

    @Bean
    AccountingTreeAdapter accountingTreeAdapter;

    @Bean
    MonthTranslator monthTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromIntegerFunction;

    @Bean
    AccountRepository accountRepository;

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

    @Bean
    QuAccHotwordRecognizer quAccHotwordRecognizer;

    @FragmentArg
    String account;
    private boolean extended;
    int streamVolume;

    @Override
    protected BaseView getBaseView() {
        return view;
    }

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        EditAccountingActivity_.intent(this).start();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (savedInstanceState != null) {
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

        accountingPlainAdapter.setAccount(account);
        accountingTreeAdapter.setAccount(account);

        resetFilter();

        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        accountCursor.moveToFirst();
        view.setAccountValue(parseValueFromIntegerFunction.apply(accountCursor.getInitialvalue()));

        view.accountingListExpandable.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                onItemClick((BookingEntryCursor) accountingTreeAdapter.getChild(groupPosition, childPosition));
                return true;
            }
        });
    }

    @Override
    protected void onViewResume() {
        view.accountingListExpandable.setFocusable(false);
        view.showFilterVisibility(extended);

        quAccHotwordRecognizer.setHotwordListener(new HotwordListener() {
            @Override
            public void onError(int error) {
                // current ignored
            }

            @Override
            public void onHotword(String hotword) {
                Toast.makeText(getActivity(), hotword, Toast.LENGTH_SHORT).show();
                if ("Neuer Eintrag".equals(hotword)) {
                    onAddAccounting();
                }
            }
        }, "Hallo", "Neuer Eintrag", "Neu");
        quAccHotwordRecognizer.startListening();

        AudioManager amanager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        // avoid beep and save current values
        streamVolume = amanager.getStreamVolume(AudioManager.STREAM_MUSIC);
        amanager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, AudioManager.FLAG_PLAY_SOUND);
        // not tested but should be necessary to avoid beep at some android sdk versions http://stackoverflow.com/questions/16370360/jelly-bean-beep-in-speech-recognition
        //amanager.setStreamMute(AudioManager.STREAM_SYSTEM, true);
    }

    @Override
    protected void onViewPause() {
        quAccHotwordRecognizer.stopListening();
        quAccHotwordRecognizer.destroy();

        AudioManager amanager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamVolume(AudioManager.STREAM_MUSIC, streamVolume, 0);
        //amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
    }

    @Override
    protected void onViewFinish() {
        quAccHotwordRecognizer.destroy();

        AudioManager amanager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);
        amanager.setStreamVolume(AudioManager.STREAM_MUSIC, streamVolume, 0);
        //amanager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
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
        if (!view.getYear().equals(year)) {
            view.setYear(year);
        }

        // avoid not necessary reload events
        if (!view.getMonth().equals(month)) {
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
            if (filterRange == FilterRange.free) {
                view.showFilterFreeRange();
            } else {
                view.hideFilterFreeRange();
            }
            DateTime startDate = getDateForRangeStartFunction.apply(filterRange, monthTranslator.translate(month), year);
            DateTime endDate = getDateForRangeEndFunction.apply(filterRange, startDate);
            accountingPlainAdapter.changeFor(startDate, endDate);
            accountingTreeAdapter.changeFor(startDate, endDate);
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
        if (GroupingOption.valueOf(groupingString) == GroupingOption.no_grouping) {
            view.setListAdapter(accountingPlainAdapter);
        } else {
            view.setListAdapter(accountingTreeAdapter);
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

    @ItemClick(R.id.listView)
    protected void onItemClick(BookingEntryCursor bookingEntryCursor) {
        EditAccountingActivity_.intent(this).bookingEntryId(bookingEntryCursor.getId()).start();
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
