package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;

@EBean
public class GetAccountsUc {

    @RootContext
    Context context;

    public CharSequence[] apply() {
        AccountCursor accounts = new AccountSelection().query(context.getContentResolver());

        String[] values = new String[accounts.getCount()];
        for (int i = 0; i < accounts.getCount(); i++) {
            accounts.moveToNext();
            values[i] = accounts.getName();
        }
        return values;
    }
}
