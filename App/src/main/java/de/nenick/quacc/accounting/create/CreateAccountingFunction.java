package de.nenick.quacc.accounting.create;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.AccountingCategoryDb;
import de.nenick.quacc.database.AccountingDb;

@EBean
public class CreateAccountingFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    AccountingCategoryDb accountingCategoryDb;

    @Bean
    AccountingDb accountingDb;

    public void apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date date, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        long accountingCategoryId = accountingCategoryDb.getIdByName(accountingCategory);

        accountingDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, date, comment, value);
    }
}
