package de.nenick.quacc.database.bookingtemplatekeyword;

import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordSelection;

public class BookingTemplateKeywordSpecById extends BookingTemplateKeywordSpec {

    private long id;

    public BookingTemplateKeywordSpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingTemplateKeywordSelection toSelection() {
        return new BookingTemplateKeywordSelection().id(id);
    }
}
