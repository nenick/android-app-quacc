package de.nenick.quacc.template;

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

    public void apply(String accountName, String type, String interval, String categoryName, String comment, int value) {
        long accountId = accountDb.getIdByName(accountName);
        long categoryId = categoryDb.getIdByName(categoryName);
        accountingTemplateDb.insert(accountId, type, interval, categoryId, comment, value);
    }
}
