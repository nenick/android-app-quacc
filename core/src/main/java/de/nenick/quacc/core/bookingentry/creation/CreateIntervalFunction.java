package de.nenick.quacc.core.bookingentry.creation;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.DbConstantValues;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookingintervalentry.BookingIntervalEntryRepository;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;

@EBean
public class CreateIntervalFunction {

    public static Date NO_DATE_GIVEN = new Date(0);

    @RootContext
    Context context;

    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingIntervalRepository bookingIntervalRepository;

    @Bean
    BookingIntervalEntryRepository bookingIntervalEntryRepository;

    @Bean
    CreateBookingEntryFunction createBookingEntryFunction;

    public void apply(String account, String direction, String interval, long categoryId, Date startDate, int amount, String comment) {
        applyWithEndDate(account, direction, interval, categoryId, startDate, DbConstantValues.NO_DATE_GIVEN, amount, comment);
    }

    public long applyWithEndDate(String account, String direction, String interval, long categoryId, Date startDate, Date endDate, int amount, String comment) {
        AccountCursor query = accountRepository.query(new AccountSpecByName(account));
        query.moveToNext();
        long accountId = query.getId();
        query.close();

        Uri uri = new BookingIntervalContentValues()
                .putAccountId(accountId)
                .putDirection(direction)
                .putInterval(interval)
                .putCategoryId(categoryId)
                .putDateStart(startDate)
                .putDateEnd(endDate)
                .putComment(comment)
                .putAmount(amount)
                .putDateLast(NO_DATE_GIVEN)
                .putDateUpdatedUntil(NO_DATE_GIVEN)
                .insert(context.getContentResolver());

        return ContentUris.parseId(uri);
    }
}
