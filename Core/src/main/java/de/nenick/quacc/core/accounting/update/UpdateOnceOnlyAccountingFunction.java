package de.nenick.quacc.core.accounting.update;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.accounting.AccountingDb;

/**
 * Intended for updating accounting with interval 'once'.
 * <p/>
 * The accounting interval is not checked or updated.
 */
@EBean
public class UpdateOnceOnlyAccountingFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    AccountingDb accountingDb;

    public void apply(long accountingId, String account, String accountingType, long categoryId, Date date, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        accountingDb.update(accountingId, accountId, accountingType, categoryId, date, comment, value);
    }
}
