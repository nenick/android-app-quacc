package de.nenick.quacc.view.accounting_overview.adapter;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;

@EViewGroup(R.layout.item_accounting)
public class AccountingItemView extends RelativeLayout {

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.interval)
    TextView interval;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.comment)
    TextView comment;

    @ViewById(R.id.value)
    TextView value;

    public AccountingItemView(Context context) {
        super(context);
    }

    public void setDate(String txt) {
        date.setText(txt);
    }

    public void setInterval(String txt) {
        interval.setText(txt);
    }

    public void setCategory(String txt) {
        category.setText(txt);
    }

    public void setComment(String txt) {
        comment.setText(txt);
    }

    public void setValue(String txt) {
        value.setText(txt);
    }

    public void showAsIncome() {
        setBackgroundColor(getResources().getColor(R.color.positiveBackground));
        date.setTextColor(getResources().getColor(R.color.positiveText));
        interval.setTextColor(getResources().getColor(R.color.positiveText));
        category.setTextColor(getResources().getColor(R.color.positiveText));
        comment.setTextColor(getResources().getColor(R.color.positiveText));
        value.setTextColor(getResources().getColor(R.color.positiveText));
    }

    public void showAsOutgoing() {
        setBackgroundColor(getResources().getColor(R.color.negativeBackground));
        date.setTextColor(getResources().getColor(R.color.negativeText));
        interval.setTextColor(getResources().getColor(R.color.negativeText));
        category.setTextColor(getResources().getColor(R.color.negativeText));
        comment.setTextColor(getResources().getColor(R.color.negativeText));
        value.setTextColor(getResources().getColor(R.color.negativeText));
    }

    public void showAsTransfer() {
        setBackgroundColor(getResources().getColor(R.color.neutralBackground));
        date.setTextColor(getResources().getColor(R.color.neutralBackground));
        interval.setTextColor(getResources().getColor(R.color.neutralBackground));
        category.setTextColor(getResources().getColor(R.color.neutralBackground));
        comment.setTextColor(getResources().getColor(R.color.neutralBackground));
        value.setTextColor(getResources().getColor(R.color.neutralBackground));
    }
}
