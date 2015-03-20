package de.nenick.quacc.accountinglist;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

@EBean
public class AccountingListAdapter extends BaseAdapter {

    @RootContext
    protected Context context;

    private List<String> accountings = new ArrayList<>();

    @Override
    public int getCount() {
        return accountings.size();
    }

    @Override
    public String getItem(int position) {
        return accountings.get(position);
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
