package de.nenick.quacc.core.accounting.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;

import de.nenick.quacc.core.accounting.change.IntervalChange;
import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.accounting.AccountingDb;
import de.nenick.quacc.database.interval.IntervalChangeDb;
import de.nenick.quacc.database.interval.IntervalDb;
import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeCursor;

@EBean
public class CreateAccountingFromIntervalFunction {

    @Bean
    IntervalDb intervalDb;

    @Bean
    IntervalChangeDb intervalChangeDb;

    @Bean
    AccountingDb accountingDb;

    @Bean
    CreateIntervalAccountingFunction createIntervalAccountingFunction;

    IntervalCursor intervalCursor;
    IntervalChangeCursor intervalChangeCursor;
    FollowUpChanges followUpChanges;
    DateTime updateUntil;
    Date lastDate;
    Date nextDate;

    public void apply(IntervalCursor intervalCursor, DateTime updateUntil) {
        resetFunction(intervalCursor, updateUntil);

        queryAllChangesForGivenInterval();
        createAccountingUntilGivenDate();
        markIntervalAsUpdatedUntilGivenDate();
        closeCreateCursors();
    }

    private void closeCreateCursors() {
        intervalChangeCursor.close();
    }

    private void resetFunction(IntervalCursor intervalCursor, DateTime updateUntil) {
        this.intervalCursor = intervalCursor;
        this.updateUntil = updateUntil;
        followUpChanges = new FollowUpChanges();
    }

    private void createAccountingUntilGivenDate() {
        createFirstAccountingIfNotDone();
        calculateNextAccountingDate();
        collectFollowUpChangesUntilLastDate();
        while (isNextAccountingDateInRangeOfUpdateTarget()) {
            createAccountingForNextDate();
            calculateNextAccountingDate();
        }
    }

    private void createAccountingForNextDate() {
        long accountingId = createIntervalAccountingFunction.apply(intervalCursor, nextDate);
        if (existChangeForNextDate()) {
            if(IntervalChange.valueOf(intervalChangeCursor.getChange()) == IntervalChange.followUp) {
                if(intervalChangeCursor.getComment() != null) {
                    followUpChanges.comment = intervalChangeCursor.getComment();
                }
                if(intervalChangeCursor.getValue() != null) {
                    followUpChanges.value = intervalChangeCursor.getValue();
                }
            }
            applyChangeForFollowUpAccounting(accountingId, followUpChanges);
            applyChangeForSpecificAccounting(accountingId, intervalChangeCursor);
        } else {
            applyChangeForFollowUpAccounting(accountingId, followUpChanges);
        }
    }

    private void applyChangeForFollowUpAccounting(long accountingId, FollowUpChanges followUpChanges) {
        if (followUpChanges.comment != null) {
            accountingDb.updateComment(accountingId, followUpChanges.comment);
        }
        if (followUpChanges.value > 0) {
            accountingDb.updateValue(accountingId, followUpChanges.value);
        }
    }

    private void applyChangeForSpecificAccounting(long accountingId, IntervalChangeCursor changesForIntervalCursor) {
        if (changesForIntervalCursor.getComment() != null) {
            accountingDb.updateComment(accountingId, changesForIntervalCursor.getComment());
        }
        if (changesForIntervalCursor.getValue() != null) {
            accountingDb.updateValue(accountingId, changesForIntervalCursor.getValue());
        }
    }

    private void markIntervalAsUpdatedUntilGivenDate() {
        intervalDb.updatedUntil(intervalCursor.getId(), lastDate, updateUntil.toDate());
    }

    private boolean isNextAccountingDateInRangeOfUpdateTarget() {
        return QuAccDateUtil.isGreaterEq(nextDate, updateUntil);
    }

    private void queryAllChangesForGivenInterval() {
        intervalChangeCursor = intervalChangeDb.getAllForInterval(intervalCursor.getId());
    }

    private void calculateNextAccountingDate() {
        lastDate = nextDate;
        AccountingInterval accountingInterval = AccountingInterval.valueOf(intervalCursor.getInterval());
        switch (accountingInterval) {
            case daily:
                nextDate = new DateTime(lastDate).plusDays(1).toDate();
                break;
            case weekly:
                nextDate = new DateTime(lastDate).plusWeeks(1).toDate();
                break;
            case eachSecondWeek:
                nextDate = new DateTime(lastDate).plusWeeks(2).toDate();
                break;
            case monthly:
                nextDate = new DateTime(lastDate).plusMonths(1).toDate();
                break;
            case eachSecondMonth:
                nextDate = new DateTime(lastDate).plusMonths(2).toDate();
                break;
            case eachThirdMonth:
                nextDate = new DateTime(lastDate).plusMonths(3).toDate();
                break;
            case eachSixMonth:
                nextDate = new DateTime(lastDate).plusMonths(6).toDate();
                break;
            case yearly:
                nextDate = new DateTime(lastDate).plusYears(1).toDate();
                break;
            default:
                throw new IllegalStateException(intervalCursor.getInterval());
        }
    }

    private void createFirstAccountingIfNotDone() {
        lastDate = intervalCursor.getDateLast();
        nextDate = lastDate;
        if (lastDate.equals(IntervalDb.NO_DATE_GIVEN)) {
            nextDate = intervalCursor.getDateStart();
            createAccountingForNextDate();
        }
    }

    private boolean existChangeForNextDate() {
        intervalChangeCursor.moveToFirst();
        while (intervalChangeCursor.moveToNext()) {
            if (intervalChangeCursor.getDate().equals(nextDate)) {
                return true;
            }
        }
        return false;
    }

    private void collectFollowUpChangesUntilLastDate() {
        IntervalChangeCursor allForIntervalUntil = intervalChangeDb.getAllForIntervalUntil(intervalCursor.getId(), lastDate);

        while (allForIntervalUntil.moveToNext()) {
            if(IntervalChange.valueOf(allForIntervalUntil.getChange()) == IntervalChange.followUp) {
                if(intervalChangeCursor.getComment() != null) {
                    followUpChanges.comment = intervalChangeCursor.getComment();
                }
                if(intervalChangeCursor.getValue() != null) {
                    followUpChanges.value = intervalChangeCursor.getValue();
                }
            }
        }

        allForIntervalUntil.close();
    }

    static class FollowUpChanges {
        String comment;
        int value;
    }
}
