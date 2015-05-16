package de.nenick.quacc.accounting.create.functions;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

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
