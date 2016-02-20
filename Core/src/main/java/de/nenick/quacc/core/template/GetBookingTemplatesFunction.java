package de.nenick.quacc.core.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.bookingtemplate.BookingTemplateRepository;
import de.nenick.quacc.database.bookingtemplate.BookingTemplateSpecAll;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;

/**
 * Deliver all existing bookingEntries bookingTemplates.
 */
@EBean
public class GetBookingTemplatesFunction {

    @Bean
    BookingTemplateRepository bookingTemplateRepository;

    public BookingTemplateCursor apply() {
        return bookingTemplateRepository.query(new BookingTemplateSpecAll());
    }
}
