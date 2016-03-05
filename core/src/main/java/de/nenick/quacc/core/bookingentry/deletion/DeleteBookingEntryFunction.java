package de.nenick.quacc.core.bookingentry.deletion;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.bookingentry.BookingEntryRepository;
import de.nenick.quacc.database.bookingentry.BookingEntrySpecById;

@EBean
public class DeleteBookingEntryFunction {

    @Bean
    BookingEntryRepository bookingEntryRepository;

    public void apply(long bookingEntryId) {
        bookingEntryRepository.delete(new BookingEntrySpecById(bookingEntryId));
    }
}
