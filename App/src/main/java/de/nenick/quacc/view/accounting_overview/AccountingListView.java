package de.nenick.quacc.view.accounting_overview;

import android.view.View;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.view.mvp.BaseView;
import de.nenick.quacc.view.accounting_overview.adapter.GroupingOptionAdapter;

@EBean
public class AccountingListView extends BaseView {

    @ViewById(R.id.listViewExpandable)
    ExpandableListView accountingListExpandable;

    @ViewById(R.id.listView)
    ListView accountingList;

    @ViewById(R.id.year)
    TextView yearField;

    @ViewById(R.id.month)
    TextView monthField;

    @ViewById(R.id.accountValue)
    TextView accountValueField;

    @ViewById(R.id.filterRange)
    Spinner filterRangeField;

    @ViewById(R.id.grouping)
    Spinner groupingField;

    @ViewById(R.id.filterLayout)
    RelativeLayout filterLayout;

    @ViewById(R.id.freeRangeFilterLayout)
    RelativeLayout filterFreeRangeLayout;

    public void showFilterVisibility(boolean visible) {
        if(visible) {
            filterLayout.setVisibility(View.VISIBLE);
        } else {
            filterLayout.setVisibility(View.GONE);
        }
    }

    public void setListAdapter(BaseExpandableListAdapter listAdapter) {
        accountingListExpandable.setVisibility(View.VISIBLE);
        accountingListExpandable.setAdapter(listAdapter);
        accountingList.setVisibility(View.GONE);
    }

    public void setListAdapter(ListAdapter listAdapter) {
        accountingList.setVisibility(View.VISIBLE);
        accountingList.setAdapter(listAdapter);
        accountingListExpandable.setVisibility(View.GONE);
    }

    public void setYear(String year) {
        yearField.setText(year);
    }

    public String getYear() {
        return yearField.getText().toString();
    }

    public void setMonth(String month) {
        monthField.setText(month);
    }

    public String getMonth() {
        return monthField.getText().toString();
    }

    public void setAccountValue(String value) {
        accountValueField.setText(value);
    }

    public void setFilterRanges(SpinnerAdapter adapter) {
        filterRangeField.setAdapter(adapter);
    }

    public <T> T getFilterRange() {
        //noinspection unchecked the caller should now what kind of item he expect
        return (T) filterRangeField.getSelectedItem();
    }

    public void showFilterFreeRange() {
        filterFreeRangeLayout.setVisibility(View.VISIBLE);
    }

    public void hideFilterFreeRange() {
        filterFreeRangeLayout.setVisibility(View.GONE);
    }

    public void setGroupingOptions(GroupingOptionAdapter adapter) {
        groupingField.setAdapter(adapter);
    }

    public void setGroupingOption(String value) {
        for (int position = 0; position < groupingField.getAdapter().getCount(); position++) {
            String item = (String) groupingField.getAdapter().getItem(position);
            if(item.equals(value)) {
                groupingField.setSelection(position);
            }
        }
    }

    public <T> T getGroupingOption() {
        //noinspection unchecked the caller should now what kind of item he expect
        return (T) groupingField.getSelectedItem();
    }

    public void setFilterRange(String value) {
        for (int position = 0; position < filterRangeField.getAdapter().getCount(); position++) {
            String item = (String) filterRangeField.getAdapter().getItem(position);
            if(item.equals(value)) {
                filterRangeField.setSelection(position);
            }
        }
    }


}
