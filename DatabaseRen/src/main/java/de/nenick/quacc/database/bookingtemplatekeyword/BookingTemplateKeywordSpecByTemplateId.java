package de.nenick.quacc.database.bookingtemplatekeyword;

import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordSelection;

public class BookingTemplateKeywordSpecByTemplateId extends BookingTemplateKeywordSpec {

    private long templateId;

    public BookingTemplateKeywordSpecByTemplateId(long templateId) {
        this.templateId = templateId;
    }

    @Override
    public BookingTemplateKeywordSelection toSelection() {
        return new BookingTemplateKeywordSelection().bookingTemplateId(templateId);
    }
}
