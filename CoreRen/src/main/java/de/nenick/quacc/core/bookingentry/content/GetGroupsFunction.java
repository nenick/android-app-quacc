package de.nenick.quacc.core.bookingentry.content;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecCategorySummeryByRange;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;

@EBean
public class GetGroupsFunction {

    @Bean
    BookingEntryRepository bookingEntryRepository;

    @Bean
    AccountRepository accountRepository;

    public BookingEntryCursor apply(String account, DateTime startDate, DateTime endDate) {
        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(account));
        accountCursor.moveToNext();
        long accountId = accountCursor.getId();
        accountCursor.close();
        startDate = startDate.minusDays(1);
        endDate = endDate.plus(1);
        return bookingEntryRepository.query(new BookingEntrySpecCategorySummeryByRange(accountId, startDate.toDate(), endDate.toDate()));
    }
}
