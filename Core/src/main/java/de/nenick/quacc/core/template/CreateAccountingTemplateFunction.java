package de.nenick.quacc.core.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.template.AccountingTemplateDb;
import de.nenick.quacc.database.category.CategoryDb;

@EBean
public class CreateAccountingTemplateFunction {

    @Bean
    AccountingTemplateDb accountingTemplateDb;

    @Bean
    AccountDb accountDb;

    @Bean
    CategoryDb categoryDb;

    public long apply(String accountName, String type, String interval, long categoryId, String comment, int value) {
        long accountId = accountDb.getIdByName(accountName);
        return accountingTemplateDb.insert(accountId, type, interval, categoryId, comment, value);
    }
}
