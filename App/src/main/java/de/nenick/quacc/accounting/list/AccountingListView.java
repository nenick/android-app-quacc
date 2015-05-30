package de.nenick.quacc.accounting.list;

import android.widget.ListAdapter;
import android.widget.ListView;
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

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        CreateAccountingActivity_.intent(context).start();
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
}
