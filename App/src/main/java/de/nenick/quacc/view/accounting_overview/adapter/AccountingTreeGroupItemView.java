package de.nenick.quacc.view.accounting_overview.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.item_accounting_group)
public class AccountingTreeGroupItemView extends RelativeLayout {

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.dateSeparator)
    TextView dateSeparator;

    @ViewById(R.id.endDate)
    TextView endDate;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.value)
    TextView value;

    public AccountingTreeGroupItemView(Context context) {
        super(context);
    }

    public void setDate(String txt) {
        date.setText(txt);
    }

    public void setEndDate(String txt) {
        endDate.setText(txt);
    }

    public void setCategory(String txt) {
        category.setText(txt);
    }

    public void setValue(String txt) {
        value.setText(txt);
    }

    public void showAsIncome() {
        setBackgroundColor(getResources().getColor(R.color.positiveBackground));
        date.setTextColor(getResources().getColor(R.color.positiveTextSmall));
        dateSeparator.setTextColor(getResources().getColor(R.color.positiveTextSmall));
        endDate.setTextColor(getResources().getColor(R.color.positiveTextSmall));
        category.setTextColor(getResources().getColor(R.color.positiveText));
        value.setTextColor(getResources().getColor(R.color.positiveText));
    }

    public void showAsOutgoing() {
        setBackgroundColor(getResources().getColor(R.color.negativeBackground));
        date.setTextColor(getResources().getColor(R.color.negativeTextSmall));
        dateSeparator.setTextColor(getResources().getColor(R.color.negativeTextSmall));
        endDate.setTextColor(getResources().getColor(R.color.negativeTextSmall));
        category.setTextColor(getResources().getColor(R.color.negativeText));
        value.setTextColor(getResources().getColor(R.color.negativeText));
    }

    public void showAsTransfer() {
        setBackgroundColor(getResources().getColor(R.color.neutralBackground));
        date.setTextColor(getResources().getColor(R.color.neutralBackground));
        dateSeparator.setTextColor(getResources().getColor(R.color.neutralBackground));
        endDate.setTextColor(getResources().getColor(R.color.neutralBackground));
        category.setTextColor(getResources().getColor(R.color.neutralBackground));
        value.setTextColor(getResources().getColor(R.color.neutralBackground));
    }
}
