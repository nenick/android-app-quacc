package de.nenick.quacc.database.bookingtemplate;

import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;

public class BookingTemplateSpecAll extends BookingTemplateSpec {

    @Override
    public BookingTemplateSelection toSelection() {
        return new BookingTemplateSelection();
    }
}
