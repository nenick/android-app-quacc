package de.nenick.quacc.core.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecByName;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;

@EBean
public class CreateBookingTemplateFunction {

    @Bean
    BookingTemplateRepository bookingTemplateRepository;

    @Bean
    AccountRepository accountRepository;

    public long apply(String accountName, String type, String interval, long categoryId, String comment) {
        long accountId = queryAccountId(accountName);
        BookingTemplateContentValues values = new BookingTemplateContentValues()
                .putAccountId(accountId)
                .putDirection(type)
                .putInterval(interval)
                .putCategoryId(categoryId)
                .putComment(comment);
        return bookingTemplateRepository.insert(values);
    }

    private long queryAccountId(String accountName) {
        AccountCursor accountCursor = accountRepository.query(new AccountSpecByName(accountName));
        long id = accountCursor.getId();
        accountCursor.close();
        return id;
    }
}
