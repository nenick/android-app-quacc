package de.nenick.quacc.accounting.list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ListAdapter;
import android.widget.ListView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.accounting.create.CreateAccountingActivity_;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class AccountingListView {

    @RootContext
    Context context;

    @ViewById(R.id.listView)
    ListView accountingList;

    @Click(R.id.btn_add_accounting)
    protected void onAddAccounting() {
        CreateAccountingActivity_.intent(context).start();
    }

    public void setListAdapter(ListAdapter listAdapter) {
        accountingList.setAdapter(listAdapter);
    }
}
