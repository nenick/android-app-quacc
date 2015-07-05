package de.nenick.quacc.database.account;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

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

    public long insert(String name, int initialValue) {
        Uri uri = new AccountContentValues().putName(name).putInitialvalue(initialValue).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public void updateInitialValue(String account, int intialValue) {
        new AccountContentValues().putInitialvalue(intialValue).update(context.getContentResolver(), new AccountSelection().name(account));
    }

    public AccountCursor getAccountByName(String account) {
        return new AccountSelection().name(account).query(context.getContentResolver());
    }

    public void deleteAll() {
        new AccountSelection().delete(context.getContentResolver());
    }

    public AccountCursor getById(long id) {
        return new AccountSelection().id(id).query(context.getContentResolver());
    }
}
