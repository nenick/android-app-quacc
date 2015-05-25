package de.nenick.quacc.accounting.create.functions;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.AccountingDb;
import de.nenick.quacc.i18n.AccountingIntervalTranslator;
import de.nenick.quacc.i18n.AccountingTypeTranslator;

@EBean
public class CreateAccountingFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    CategoryDb categoryDb;

    @Bean
    AccountingDb accountingDb;

    public void apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date date, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        long accountingCategoryId = categoryDb.getIdByName(accountingCategory);

        accountingDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, date, comment, value);
    }
}
