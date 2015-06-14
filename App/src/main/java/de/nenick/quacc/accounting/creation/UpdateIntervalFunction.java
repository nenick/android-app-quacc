package de.nenick.quacc.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;

import de.nenick.quacc.common.util.QuAccDateUtil;
import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.IntervalDb;
import de.nenick.quacc.database.provider.interval.IntervalCursor;

@EBean
public class UpdateIntervalFunction {

    @Bean
    IntervalDb intervalDb;

    @Bean
    CreateIntervalAccountingFunction createIntervalAccountingFunction;

    public void apply(IntervalCursor intervalCursor, DateTime updateUntil) {
        Date lastDate = createFirstAccountingIfNotDone(intervalCursor);
        Date nextDate = calculateNextDate(intervalCursor, lastDate);
        while (QuAccDateUtil.isGreaterEq(nextDate, updateUntil)) {
            update(intervalCursor, nextDate);
            lastDate = nextDate;
            nextDate = calculateNextDate(intervalCursor, lastDate);
        }
        intervalDb.updatedUntil(intervalCursor.getId(), updateUntil.toDate());
    }

    private Date calculateNextDate(IntervalCursor intervalCursor, Date lastDate) {
        AccountingInterval accountingInterval = AccountingInterval.valueOf(intervalCursor.getInterval());
        switch (accountingInterval) {
            case daily:
                return new DateTime(lastDate).plusDays(1).toDate();
            case weekly:
                return new DateTime(lastDate).plusWeeks(1).toDate();
            case eachSecondWeek:
                return new DateTime(lastDate).plusWeeks(2).toDate();
            case monthly:
                return new DateTime(lastDate).plusMonths(1).toDate();
            case eachSecondMonth:
                return new DateTime(lastDate).plusMonths(2).toDate();
            case eachThirdMonth:
                return new DateTime(lastDate).plusMonths(3).toDate();
            case eachSixMonth:
                return new DateTime(lastDate).plusMonths(6).toDate();
            case yearly:
                return new DateTime(lastDate).plusYears(1).toDate();
            default:
                throw new IllegalStateException(intervalCursor.getInterval());
        }
    }

    private Date createFirstAccountingIfNotDone(IntervalCursor intervalCursor) {
        Date nextDate = intervalCursor.getDateUpdatedUntil();
        if (intervalCursor.getDateUpdatedUntil().equals(IntervalDb.NO_DATE_GIVEN)) {
            nextDate = intervalCursor.getDateStart();
            update(intervalCursor, nextDate);
        }
        return nextDate;
    }

    private void update(IntervalCursor intervalCursor, Date date) {
        createIntervalAccountingFunction.apply(intervalCursor, date);
    }

}
