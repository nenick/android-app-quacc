package de.nenick.quacc.core.bookingentry.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import java.util.Date;

import de.nenick.quacc.core.bookinginterval.BookingIntervalOption;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.database.DbConstantValues;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecById;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalSpecById;
import de.nenick.quacc.database.bookingintervalchange.BookingIntervalChangeRepository;
import de.nenick.quacc.database.bookingintervalchange.BookingIntervalChangeSpecByIntervalId;
import de.nenick.quacc.database.bookingintervalchange.BookingIntervalChangeSpecByIntervalIdUntilDate;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeCursor;

@EBean
public class CreateBookingEntryFromIntervalFunction {

    @Bean
    BookingIntervalRepository bookingIntervalRepository;

    @Bean
    BookingIntervalChangeRepository bookingIntervalChangeRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    @Bean
    CreateBookingIntervalEntryFunction createBookingIntervalEntryFunction;

    BookingIntervalCursor intervalCursor;
    BookingIntervalChangeCursor intervalChangeCursor;
    FollowUpChanges followUpChanges;
    DateTime updateUntil;
    Date lastDate;
    Date nextDate;

    public void apply(BookingIntervalCursor intervalCursor, DateTime updateUntil) {
        resetFunction(intervalCursor, updateUntil);

        queryAllChangesForGivenInterval();
        createAccountingUntilGivenDate();
        markIntervalAsUpdatedUntilGivenDate();
        closeCreateCursors();
    }

    private void closeCreateCursors() {
        intervalChangeCursor.close();
    }

    private void resetFunction(BookingIntervalCursor intervalCursor, DateTime updateUntil) {
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
        long bookingEntryId = createBookingIntervalEntryFunction.apply(intervalCursor, nextDate);
        if (existChangeForNextDate()) {
            if (intervalChangeCursor.getFollowUp()) {
                if (intervalChangeCursor.getComment() != null) {
                    followUpChanges.comment = intervalChangeCursor.getComment();
                }
                if (intervalChangeCursor.getAmount() != null) {
                    followUpChanges.amount = intervalChangeCursor.getAmount();
                }
            }
            applyChangeForFollowUpBookingEntry(bookingEntryId, followUpChanges);
            applyChangeForSpecificBookingEntry(bookingEntryId, intervalChangeCursor);
        } else {
            applyChangeForFollowUpBookingEntry(bookingEntryId, followUpChanges);
        }
    }

    private void applyChangeForFollowUpBookingEntry(long bookingEntryId, FollowUpChanges followUpChanges) {
        if (followUpChanges.comment != null) {
            BookingEntryContentValues values = new BookingEntryContentValues().putComment(followUpChanges.comment);
            bookingEntryRepository.update(values, new BookingEntrySpecById(bookingEntryId));
        }
        if (followUpChanges.amount > 0) {
            BookingEntryContentValues values = new BookingEntryContentValues().putAmount(followUpChanges.amount);
            bookingEntryRepository.update(values, new BookingEntrySpecById(bookingEntryId));
        }
    }

    private void applyChangeForSpecificBookingEntry(long bookingEntryId, BookingIntervalChangeCursor changesForIntervalCursor) {
        if (changesForIntervalCursor.getComment() != null) {
            BookingEntryContentValues values = new BookingEntryContentValues().putComment(followUpChanges.comment);
            bookingEntryRepository.update(values, new BookingEntrySpecById(bookingEntryId));
        }
        if (changesForIntervalCursor.getAmount() != null) {
            BookingEntryContentValues values = new BookingEntryContentValues().putAmount(followUpChanges.amount);
            bookingEntryRepository.update(values, new BookingEntrySpecById(bookingEntryId));
        }
    }

    private void markIntervalAsUpdatedUntilGivenDate() {
        BookingIntervalContentValues values = new BookingIntervalContentValues()
                .putDateLast(lastDate)
                .putDateUpdatedUntil(updateUntil.toDate());
        bookingIntervalRepository.update(values, new BookingIntervalSpecById(intervalCursor.getId()));
    }

    private boolean isNextAccountingDateInRangeOfUpdateTarget() {
        return QuAccDateUtil.isGreaterEq(nextDate, updateUntil) &&
                (intervalCursor.getDateEnd().equals(CreateIntervalFunction.NO_DATE_GIVEN) || QuAccDateUtil.isGreaterEq(nextDate, new DateTime(intervalCursor.getDateEnd())));
    }

    private void queryAllChangesForGivenInterval() {
        intervalChangeCursor = bookingIntervalChangeRepository.query(new BookingIntervalChangeSpecByIntervalId(intervalCursor.getId()));
    }

    private void calculateNextAccountingDate() {
        lastDate = nextDate;
        BookingIntervalOption bookingIntervalOption = BookingIntervalOption.valueOf(intervalCursor.getInterval());
        switch (bookingIntervalOption) {
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
        if (lastDate.equals(DbConstantValues.NO_DATE_GIVEN)) {
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
        BookingIntervalChangeCursor allForIntervalUntil = bookingIntervalChangeRepository.query(new BookingIntervalChangeSpecByIntervalIdUntilDate(intervalCursor.getId(), lastDate));

        while (allForIntervalUntil.moveToNext()) {
            if (allForIntervalUntil.getFollowUp()) {
                if (intervalChangeCursor.getComment() != null) {
                    followUpChanges.comment = intervalChangeCursor.getComment();
                }
                if (intervalChangeCursor.getAmount() != null) {
                    followUpChanges.amount = intervalChangeCursor.getAmount();
                }
            }
        }

        allForIntervalUntil.close();
    }

    static class FollowUpChanges {
        String comment;
        int amount;
    }
}
