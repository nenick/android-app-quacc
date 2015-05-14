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
        type.setText(accountingCursor.getAccountingType().name());
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
