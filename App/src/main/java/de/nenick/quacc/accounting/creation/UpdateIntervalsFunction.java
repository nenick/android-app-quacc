package de.nenick.quacc.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.IntervalDb;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

@EBean
public class UpdateIntervalsFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    IntervalDb intervalDb;

    @Bean
    UpdateIntervalFunction updateIntervalFunction;

    public void apply(String account, DateTime updateUntil) {
        AccountCursor accountByName = accountDb.getAccountByName(account);
        accountByName.moveToFirst();

        IntervalCursor intervalCursor = intervalDb.getAllForAccountNotUpdatedUntil(accountByName.getId(), updateUntil.toDate());
        while (intervalCursor.moveToNext()) {
            updateIntervalFunction.apply(intervalCursor, updateUntil);
        }
        accountByName.close();
    }
}
