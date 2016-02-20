package de.nenick.quacc.core.template;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.bookingtemplatekeyword.BookingTemplateKeywordRepository;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordContentValues;

@EBean
public class AddTemplateKeywordFunction {

    @Bean
    BookingTemplateKeywordRepository bookingTemplateKeywordRepository;

    public void apply(String text, long templateId) {
        BookingTemplateKeywordContentValues values = new BookingTemplateKeywordContentValues()
                .putText(text)
                .putBookingTemplateId(templateId);
        bookingTemplateKeywordRepository.insert(values);
    }
}
