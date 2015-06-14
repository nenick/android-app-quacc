package de.nenick.quacc.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.AccountDb;
import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.IntervalAccountingDb;
import de.nenick.quacc.database.IntervalDb;

@EBean
public class CreateIntervalFunction {

    @Bean
    AccountDb accountDb;

    @Bean
    CategoryDb categoryDb;

    @Bean
    IntervalDb intervalDb;

    @Bean
    IntervalAccountingDb intervalAccountingDb;

    @Bean
    CreateAccountingFunction createAccountingFunction;

    public void apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date startDate, int value, String comment) {
        applyWithEndDate(account, accountingType, accountingInterval, accountingCategory, startDate, IntervalDb.NO_DATE_GIVEN, value, comment);
    }

    public long applyWithEndDate(String account, String accountingType, String accountingInterval, String accountingCategory, Date startDate, Date endDate, int value, String comment) {
        long accountId = accountDb.getIdByName(account);
        long accountingCategoryId = categoryDb.getIdByName(accountingCategory);
        return intervalDb.insert(accountId, accountingType, accountingInterval, accountingCategoryId, startDate, endDate, comment, value);
    }
}
