package de.nenick.quacc.accounting.interval.functions;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.accounting.create.functions.CreateAccountingFunction;
import de.nenick.quacc.database.IntervalAccountingDb;
import de.nenick.quacc.database.IntervalDb;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

@EBean
public class CreateIntervalAccountingFunction {

    @Bean
    IntervalDb intervalDb;

    @Bean
    IntervalAccountingDb intervalAccountingDb;

    @Bean
    CreateAccountingFunction createAccountingFunction;

    public void apply(IntervalCursor intervalCursor, Date date) {
        long accountingId = createAccountingFunction.apply(intervalCursor.getAccountName(), intervalCursor.getType(), intervalCursor.getInterval(), intervalCursor.getCategoryName(), date, intervalCursor.getValue(), intervalCursor.getComment());
        intervalAccountingDb.insert(intervalCursor.getId(), accountingId);
    }

}
