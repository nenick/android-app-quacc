package de.nenick.quacc.core.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountDb;
import de.nenick.quacc.database.category.CategoryDb;
import de.nenick.quacc.database.interval.IntervalAccountingDb;
import de.nenick.quacc.database.interval.IntervalDb;

@EBean
public class CreateIntervalFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    IntervalDb intervalDb;

    @Bean
    IntervalAccountingDb intervalAccountingDb;

    @Bean
    CreateAccountingFunction createAccountingFunction;

    public void apply(String account, String accountingType, String accountingInterval, long categoryId, Date startDate, int value, String comment) {
        applyWithEndDate(account, accountingType, accountingInterval, categoryId, startDate, IntervalDb.NO_DATE_GIVEN, value, comment);
    }

    public long applyWithEndDate(String account, String accountingType, String accountingInterval, long categoryId, Date startDate, Date endDate, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        return intervalDb.insert(accountId, accountingType, accountingInterval, categoryId, startDate, endDate, comment, value);
    }
}
