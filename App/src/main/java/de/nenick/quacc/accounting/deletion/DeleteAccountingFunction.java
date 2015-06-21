package de.nenick.quacc.accounting.deletion;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.accounting.AccountingDb;

@EBean
public class DeleteAccountingFunction {

    @Bean
    AccountingDb accountingDb;

    public void apply(long accountingId) {
        accountingDb.delete(accountingId);
    }
}
