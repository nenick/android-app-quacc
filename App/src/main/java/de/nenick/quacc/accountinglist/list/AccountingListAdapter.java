package de.nenick.quacc.accountinglist.list;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class AccountingListAdapter extends BaseAdapter {

    @RootContext
    protected Context context;

    private CharSequence[] accountings = new CharSequence[]{};

    public void swapAccountings(CharSequence[] accountings) {
        this.accountings = accountings;
    }

    @Override
    public int getCount() {
        return accountings.length;
    }

    @Override
    public String getItem(int position) {
        return accountings[position].toString();
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        AccountingListItem view;

        if (convertView == null) {
            view = AccountingListItem_.build(context);
        } else {
            view = (AccountingListItem) convertView;
        }

        view.bind(getItem(position), "08,15");

        return view;
    }
}
