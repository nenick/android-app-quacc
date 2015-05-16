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

import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class AccountingListAdapter extends CursorAdapter {

    @RootContext
    Context context;

    @Bean
    GetAccountingListFunction getAccountingListFunction;

    public AccountingListAdapter() {
        super(null, null, true);
    }

    @AfterInject
    protected void afterInject() {
        mContext = context;
        swapCursor(getAccountingListFunction.apply());
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return AccountingListItem_.build(context);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ((AccountingListItem) view).bind((AccountingCursor) cursor);
    }
}
