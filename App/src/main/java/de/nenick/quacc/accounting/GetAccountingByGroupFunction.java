package de.nenick.quacc.accounting;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.AccountingDb;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class GetAccountingByGroupFunction {

    @Bean
    AccountingDb accountingDb;

    @Bean
    AccountDb accountDb;

    public AccountingCursor apply(String account, long categoryId, String type, DateTime startDate, DateTime endDate) {
        long accountId = accountDb.getIdByName(account);
        startDate = startDate.minusDays(1);
        endDate = endDate.plus(1);
        return accountingDb.getForGroupBetween(accountId, categoryId, type, startDate.toDate(), endDate.toDate());
    }
}
