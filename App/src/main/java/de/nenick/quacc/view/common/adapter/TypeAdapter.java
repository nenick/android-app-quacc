package de.nenick.quacc.view.common.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.i18n.AccountingTypeTranslator;

@EBean
public class TypeAdapter extends ArrayAdapter<String> {

    @Bean
    AccountingTypeTranslator accountingTypeTranslator;

    public TypeAdapter(Context context) {
        super(context, android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getView(position, convertView, parent);
        view.setText(accountingTypeTranslator.translate(view.getText().toString()));
        return view;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView view = (TextView) super.getDropDownView(position, convertView, parent);
        view.setText(accountingTypeTranslator.translate(view.getText().toString()));
        return view;
    }
}
