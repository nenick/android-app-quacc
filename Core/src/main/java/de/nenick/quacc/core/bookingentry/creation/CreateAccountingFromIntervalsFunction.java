package de.nenick.quacc.core.bookingentry.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookinginterval.BookingIntervalRepository;
import de.nenick.quacc.database.bookinginterval.BookingIntervalSpecNeedUpdate;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;

@EBean
public class CreateAccountingFromIntervalsFunction {

    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingIntervalRepository bookingIntervalRepository;

    @Bean
    CreateBookingEntryFromIntervalFunction createBookingEntryFromIntervalFunction;

    public void apply(String accountName, DateTime updateUntil) {
        AccountCursor account = accountRepository.query(new AccountSpecByName(accountName));
        account.moveToFirst();

        BookingIntervalCursor intervalCursor = bookingIntervalRepository.query(new BookingIntervalSpecNeedUpdate(account.getId(), updateUntil.toDate()));
        while (intervalCursor.moveToNext()) {
            createBookingEntryFromIntervalFunction.apply(intervalCursor, updateUntil);
        }
        account.close();
    }
}
