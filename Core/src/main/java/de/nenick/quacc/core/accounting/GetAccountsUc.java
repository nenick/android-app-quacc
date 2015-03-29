package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.provider.account.AccountCursor;

@EBean
public class GetAccountsUc {

    @Inject
    AccountRepository accountRepository;

    public CharSequence[] apply() {
        AccountCursor accounts = accountRepository.getAccounts();
        String[] values = new String[accounts.getCount()];
        for (int i = 0; i < accounts.getCount(); i++) {
            accounts.moveToNext();
            values[i] = accounts.getName();
        }
        return values;
    }

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }
}
