package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

@EBean
public class AccountRepository {

    @RootContext
    Context context;

    public AccountCursor getAccounts() {
        return new AccountSelection().query(context.getContentResolver());
    }

    public long getIdFor(String account) {
        AccountCursor query = new AccountSelection().name(account).query(context.getContentResolver());
        query.moveToNext();
        return query.getId();
    }
}
