package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class GetAccountingListUc {

    @Inject
    AccountingRepository accountingRepository;

    public AccountingCursor apply() {
        return accountingRepository.getAccountings();
    }

    @AfterInject
    public void afterInject() {
        DaggerSupport.inject(this);
    }
}
