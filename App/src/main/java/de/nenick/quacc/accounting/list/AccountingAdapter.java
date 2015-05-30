package de.nenick.quacc.accounting.list;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.View;
import android.view.ViewGroup;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.joda.time.DateTime;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import de.nenick.quacc.accounting.list.functions.GetAccountingListFunction;
import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.i18n.AccountingTypeTranslator;

@EBean
public class AccountingAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetAccountingListFunction getAccountingListFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    AccountingTypeTranslator accountingTypeTranslator;

    public AccountingAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
        swapCursor(getAccountingListFunction.apply());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return AccountingItemView_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView((AccountingItemView) view, (AccountingCursor) cursor);
    }

    private void bindView(AccountingItemView view, AccountingCursor accountingCursor) {
        AccountingType accountingType = AccountingType.valueOf(accountingCursor.getType());
        switch (accountingType) {
            case incoming:
                view.showAsIncome();
                break;
            case outgoing:
                view.showAsOutgoing();
        }

        DateFormat df = QuAccDateUtil.getDefaultDateFormat();
        view.setType(accountingTypeTranslator.translate(accountingType));
        view.setDate(df.format(accountingCursor.getDate()));
        view.setInterval(accountingIntervalTranslator.translate(accountingCursor.getInterval()));
        view.setCategory(accountingCursor.getCategoryName());
        view.setComment(accountingCursor.getComment());
        view.setValue(createValueString(accountingCursor));
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

    public void updateFor(String month, String year) {
        Date startDate = QuAccDateUtil.asDate(1, month, year);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        Date endDate = QuAccDateUtil.asDate(calendar.getActualMaximum(Calendar.DAY_OF_MONTH), month, year);

        AccountingCursor apply = getAccountingListFunction.apply(new DateTime(startDate), new DateTime(endDate));
        swapCursor(apply);
    }
}