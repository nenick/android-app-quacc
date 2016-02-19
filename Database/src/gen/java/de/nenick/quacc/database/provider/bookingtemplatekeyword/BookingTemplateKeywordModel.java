package de.nenick.quacc.database.provider.bookingtemplatekeyword;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Assign key word for template
 */
public interface BookingTemplateKeywordModel extends BaseModel {

    /**
     * Word or phrase to match template.
     * Cannot be {@code null}.
     */
    @NonNull
    String getText();

    /**
     * Referenced booking template.
     */
    long getBookingTemplateId();
}
