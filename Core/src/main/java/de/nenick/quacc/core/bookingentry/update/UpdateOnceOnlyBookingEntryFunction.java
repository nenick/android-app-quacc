package de.nenick.quacc.core.bookingentry.update;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecById;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;

/**
 * Intended for updating bookingEntries with interval 'once'.
 * <p/>
 * The bookingEntries interval is not checked or updated.
 */
@EBean
public class UpdateOnceOnlyBookingEntryFunction {

    @Bean
    AccountRepository accountRepository;

    @Bean
    BookingEntryRepository bookingEntryRepository;

    public void apply(long bookingEntryId, String account, String direction, long categoryId, Date date, int amount, String comment) {
        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        long accountId = accountCursor.getId();
        accountCursor.close();

        BookingEntryContentValues values = new BookingEntryContentValues()
                .putAccountId(accountId)
                .putCategoryId(categoryId)
                .putDirection(direction)
                .putDate(date)
                .putComment(comment)
                .putAmount(amount);

        bookingEntryRepository.update(values, new BookingEntrySpecById(bookingEntryId));
    }
}
