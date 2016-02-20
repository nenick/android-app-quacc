package de.nenick.quacc.core.bookingentry.creation;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.Date;

import de.nenick.quacc.database.bookingintervalentry.BookingIntervalEntryRepository;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryContentValues;

@EBean
public class CreateBookingIntervalEntryFunction {

    @Bean
    BookingIntervalEntryRepository bookingIntervalEntryRepository;

    @Bean
    CreateBookingEntryFunction createBookingEntryFunction;

    public long apply(BookingIntervalCursor intervalCursor, Date date) {
        long bookingEntryId = createBookingEntryFunction.apply(intervalCursor.getAccountName(), intervalCursor.getDirection(), intervalCursor.getInterval(), intervalCursor.getCategoryId(), date, intervalCursor.getAmount(), intervalCursor.getComment());
        BookingIntervalEntryContentValues values = new BookingIntervalEntryContentValues()
                .putBookingEntryId(bookingEntryId)
                .putBookingIntervalId(intervalCursor.getId());
        bookingIntervalEntryRepository.insert(values);
        return bookingEntryId;
    }

}
