package de.nenick.quacc.core.account;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EBean
public class GetAccountsFunction {

    @RootContext
    Context context;

    @Bean
    AccountDb accountDb;

    public CharSequence[] apply() {
        AccountCursor accounts = accountDb.getAll();
        String[] values = new String[accounts.getCount()];
        for (int i = 0; i < accounts.getCount(); i++) {
            accounts.moveToNext();
            values[i] = accounts.getName();
        }
        accounts.close();
        return values;
    }
}
