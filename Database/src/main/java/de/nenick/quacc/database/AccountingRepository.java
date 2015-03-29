package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;

@EBean
public class AccountingRepository {

    @RootContext
    Context context;

    public AccountingCursor getAccountings() {
        return new AccountingSelection().query(context.getContentResolver());
    }
}
