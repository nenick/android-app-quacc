package de.nenick.quacc.view.accounting_overview.adapter;

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

import de.nenick.quacc.core.bookingentry.content.GetAccountingByRangeFunction;
import de.nenick.quacc.core.bookingentry.creation.CreateAccountingFromIntervalsFunction;
import de.nenick.quacc.core.bookingentry.direction.BookingDirectionOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;

@EBean
public class AccountingPlainAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetAccountingByRangeFunction getAccountingByRangeFunction;

    @Bean
    AccountingIntervalTranslator accountingIntervalTranslator;

    @Bean
    ParseValueFromIntegerFunction parseValueFromInteger;

    @Bean
    CreateAccountingFromIntervalsFunction createAccountingFromIntervalsFunction;

    private String account;

    public AccountingPlainAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return AccountingPlainItemView_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        bindView((AccountingPlainItemView) view, (BookingEntryCursor) cursor);
    }

    private void bindView(AccountingPlainItemView view, BookingEntryCursor bookingEntryCursor) {
        BookingDirectionOption bookingDirectionOption = BookingDirectionOption.valueOf(bookingEntryCursor.getDirection());
        switch (bookingDirectionOption) {
            case incoming:
                view.showAsIncome();
                break;
            case outgoing:
                view.showAsOutgoing();
        }

        view.setDate(QuAccDateUtil.toString(bookingEntryCursor.getDate()));
        view.setInterval(accountingIntervalTranslator.translate(bookingEntryCursor.getInterval()));
        view.setCategory(bookingEntryCursor.getCategoryName());
        view.setComment(bookingEntryCursor.getComment());
        view.setValue(createValueString(bookingEntryCursor));
    }

    private String createValueString(BookingEntryCursor bookingEntryCursor) {
        int value = bookingEntryCursor.getAmount();
        return parseValueFromInteger.apply(value);
    }

    public void changeFor(DateTime startDate, DateTime endDate) {
        createAccountingFromIntervalsFunction.apply(account, endDate);
        BookingEntryCursor apply = getAccountingByRangeFunction.apply(account, startDate, endDate);
        changeCursor(apply);
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
