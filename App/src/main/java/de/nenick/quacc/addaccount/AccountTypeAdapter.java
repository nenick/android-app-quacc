package de.nenick.quacc.addaccount;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AccountTypeAdapter extends BaseAdapter {

    String[] accountType = new String[]{"Ausgabe, Einnahme"};

    @Override
    public int getCount() {
        return accountType.length;
    }

    @Override
    public Object getItem(int position) {
        return accountType[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
