package de.nenick.quacc.core.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.interval.IntervalDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

@EBean
public class CreateAccountingFromIntervalsFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    IntervalDb intervalDb;

    @Bean
    CreateAccountingFromIntervalFunction createAccountingFromIntervalFunction;

    public void apply(String account, DateTime updateUntil) {
        AccountCursor accountByName = accountDb.getAccountByName(account);
        accountByName.moveToFirst();

        IntervalCursor intervalCursor = intervalDb.getAllForAccountNotUpdatedUntil(accountByName.getId(), updateUntil.toDate());
        while (intervalCursor.moveToNext()) {
            createAccountingFromIntervalFunction.apply(intervalCursor, updateUntil);
        }
        accountByName.close();
    }
}
