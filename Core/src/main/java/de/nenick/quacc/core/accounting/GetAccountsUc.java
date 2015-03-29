package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EBean
public class GetAccountsUc {

    @RootContext
    Context context;

    @Inject
    AccountRepository accountRepository;

    public CharSequence[] apply() {
        AccountCursor accounts = accountRepository.getAccounts();
        String[] accountsNames = new String[accounts.getCount()];
        for (int i = 0; i < accounts.getCount(); i++) {
            accounts.moveToNext();
            accountsNames[i] = accounts.getName();
        }
        return accountsNames;
    }

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }
}
