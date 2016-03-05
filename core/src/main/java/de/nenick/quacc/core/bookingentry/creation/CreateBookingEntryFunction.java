package de.nenick.quacc.core.bookingentry.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;

@EBean
public class CreateBookingEntryFunction {

    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    public long apply(String account, String direction, String interval, long categoryId, Date date, int amount, String comment) {
        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        accountCursor.moveToNext();
        long accountId = accountCursor.getId();
        accountCursor.close();

        BookingEntryContentValues values = new BookingEntryContentValues()
                .putAccountId(accountId)
                .putCategoryId(categoryId)
                .putDirection(direction)
                .putInterval(interval)
                .putDate(date)
                .putComment(comment)
                .putAmount(amount);
        return bookingEntryRepository.insert(values);
    }
}
