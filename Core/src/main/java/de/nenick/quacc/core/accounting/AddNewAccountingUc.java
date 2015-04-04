package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountingCategoryRepository;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;

@EBean
public class AddNewAccountingUc {

    @Inject
    AccountingRepository accountingRepository;

    @Inject
    AccountRepository accountRepository;

    @Inject
    AccountingCategoryRepository accountingCategoryRepository;

    public void apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date date, int value, String comment) {
        long accountId = accountRepository.getIdFor(account);
        long accountingCategoryId = accountingCategoryRepository.getIdFor(accountingCategory);
        accountingRepository.insertAccounting(accountId, AccountingType.valueOf(accountingType), AccountingInterval.valueOf(accountingInterval), accountingCategoryId, date, value, comment);
    }

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }
}
