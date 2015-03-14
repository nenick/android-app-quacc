package de.nenick.quacc.addaccounting;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class AccountingTypeAdapter extends BaseAdapter {

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
