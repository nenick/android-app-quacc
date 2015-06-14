package de.nenick.quacc.accounting.list;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.CreateAccountingActivity_;
import de.nenick.quacc.common.mvp.BaseView;

@EBean
public class AccountingListView extends BaseView {

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

    @ViewById(R.id.filterLayout)
    RelativeLayout toggleFilterLayout;

    @Click(R.id.filterToggle)
    protected void onToggleFilterVisibility() {
        if(toggleFilterLayout.getVisibility() == View.VISIBLE) {
            toggleFilterLayout.setVisibility(View.GONE);
        } else {
            toggleFilterLayout.setVisibility(View.VISIBLE);
        }
    }

    public void setListAdapter(ListAdapter listAdapter) {
        accountingList.setAdapter(listAdapter);
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
}
