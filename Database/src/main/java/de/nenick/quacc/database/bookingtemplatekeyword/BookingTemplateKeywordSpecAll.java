package de.nenick.quacc.database.bookingtemplatekeyword;

import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordSelection;

public class BookingTemplateKeywordSpecAll extends BookingTemplateKeywordSpec {

    @Override
    public BookingTemplateKeywordSelection toSelection() {
        return new BookingTemplateKeywordSelection();
    }
}
