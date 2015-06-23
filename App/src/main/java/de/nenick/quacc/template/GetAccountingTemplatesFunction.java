package de.nenick.quacc.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateCursor;
import de.nenick.quacc.database.template.AccountingTemplateDb;

/**
 * Deliver all existing accounting templates.
 */
@EBean
public class GetAccountingTemplatesFunction {

    @Bean
    AccountingTemplateDb accountingTemplateDb;

    public AccountingTemplateCursor apply() {
        return accountingTemplateDb.getAll();
    }
}
