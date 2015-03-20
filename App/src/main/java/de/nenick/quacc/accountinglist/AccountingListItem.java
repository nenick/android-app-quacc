package de.nenick.quacc.accountinglist;

import android.content.Context;
import android.widget.TextView;
import android.widget.TwoLineListItem;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(android.R.layout.simple_list_item_2)
public class AccountingListItem extends TwoLineListItem {

    @ViewById(android.R.id.text1)
    TextView description;

    @ViewById(android.R.id.text2)
    TextView value;

    public AccountingListItem(Context context) {
        super(context);
    }

    public void bind(String desc, String val) {
        description.setText(desc);
        value.setText(val);
    }
}
