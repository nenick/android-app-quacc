package de.nenick.quacc.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.category.CategoryDb;

@EBean
public class CreateAccountingFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    CategoryDb categoryDb;

    @Bean
    AccountingDb accountingDb;

    public long apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date date, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        long accountingCategoryId = categoryDb.getIdByName(accountingCategory);

        return accountingDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, date, comment, value);
    }
}
