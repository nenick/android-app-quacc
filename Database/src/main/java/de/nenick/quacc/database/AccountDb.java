package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountContentValues;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

@EBean
public class AccountDb {

    @RootContext
    Context context;

    public long getIdByName(String account) {
        AccountCursor query = new AccountSelection().name(account).query(context.getContentResolver());
        query.moveToNext();
        long id = query.getId();
        query.close();
        return id;
    }

    public AccountCursor getAll() {
        return new AccountSelection().query(context.getContentResolver());
    }

    public void setInitialValue(String account, int value) {
        new AccountContentValues().putInitialvalue(value).update(context.getContentResolver(), new AccountSelection().name(account));
    }

    public AccountCursor getAccountByName(String account) {
        return new AccountSelection().name(account).query(context.getContentResolver());
    }
}
