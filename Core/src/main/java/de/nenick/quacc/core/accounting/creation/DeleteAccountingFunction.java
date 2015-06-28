package de.nenick.quacc.core.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.category.CategoryDb;

@EBean
public class DeleteAccountingFunction {

    @Bean
    AccountingDb accountingDb;

    public void apply(long accountingId) {
        accountingDb.deleteById(accountingId);
    }
}
