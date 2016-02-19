package de.nenick.quacc.database.bookingtemplate;

import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;

public class BookingTemplateSpecById extends BookingTemplateSpec {

    private long id;

    public BookingTemplateSpecById(long id) {
        this.id = id;
    }

    @Override
    public BookingTemplateSelection toSelection() {
        return new BookingTemplateSelection().id(id);
    }
}
