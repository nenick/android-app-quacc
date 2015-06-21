package de.nenick.quacc.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.interval.IntervalAccountingDb;
import de.nenick.quacc.database.interval.IntervalDb;
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
