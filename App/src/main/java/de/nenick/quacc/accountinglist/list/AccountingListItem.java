package de.nenick.quacc.accountinglist.list;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;

import de.nenick.quacc.R;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EViewGroup(R.layout.fragment_accounting_list_item)
public class AccountingListItem extends RelativeLayout {

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.type)
    TextView type;

    @ViewById(R.id.interval)
    TextView interval;

    @ViewById(R.id.category)
    TextView category;

    @ViewById(R.id.comment)
    TextView comment;

    @ViewById(R.id.value)
    TextView value;

    public AccountingListItem(Context context) {
        super(context);
    }

    public void bind(AccountingCursor accountingCursor) {
        DateFormat df = QuAccDateUtil.getDefaultDateFormat();
        date.setText(df.format(accountingCursor.getAccountingDate()));
        String accountingType = accountingCursor.getAccountingType().name();
        if(accountingType.equals("Ausgabe")) {
            setBackgroundColor(getResources().getColor(R.color.negativeBackground));
            date.setTextColor(getResources().getColor(R.color.negativeText));
            type.setTextColor(getResources().getColor(R.color.negativeText));
            interval.setTextColor(getResources().getColor(R.color.negativeText));
            category.setTextColor(getResources().getColor(R.color.negativeText));
            comment.setTextColor(getResources().getColor(R.color.negativeText));
            value.setTextColor(getResources().getColor(R.color.negativeText));
        }
        if(accountingType.equals("Einnahme")) {
            setBackgroundColor(getResources().getColor(R.color.positiveBackground));
            date.setTextColor(getResources().getColor(R.color.positiveText));
            type.setTextColor(getResources().getColor(R.color.positiveText));
            interval.setTextColor(getResources().getColor(R.color.positiveText));
            category.setTextColor(getResources().getColor(R.color.positiveText));
            comment.setTextColor(getResources().getColor(R.color.positiveText));
            value.setTextColor(getResources().getColor(R.color.positiveText));
        }
        type.setText(accountingType);
        interval.setText(accountingCursor.getAccountingInterval().name());
        category.setText(accountingCursor.getAccountingCategoryName());
        comment.setText(accountingCursor.getComment());
        String valueString = createValueString(accountingCursor);
        this.value.setText(valueString);
    }

    private String createValueString(AccountingCursor accountingCursor) {
        int value = accountingCursor.getValue();
        String valueString = String.valueOf(value);
        if (value < 10) {
            valueString = "0,0" + valueString;
        } else if (value < 100) {
            valueString = "0," + valueString;
        } else {
            String decimal = valueString.substring(valueString.length() - 2, valueString.length());
            valueString = valueString.substring(0, valueString.length() - 2) + "," + decimal;
        }
        return valueString;
    }
}
