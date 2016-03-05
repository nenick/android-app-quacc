package de.nenick.quacc.database.provider.bookingtemplatekeyword;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_template_keyword} table.
 */
public class BookingTemplateKeywordContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingTemplateKeywordColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingTemplateKeywordSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Word or phrase to match template.
     */
    public BookingTemplateKeywordContentValues putText(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("text must not be null");
        mContentValues.put(BookingTemplateKeywordColumns.TEXT, value);
        return this;
    }


    /**
     * Referenced booking template.
     */
    public BookingTemplateKeywordContentValues putBookingTemplateId(long value) {
        mContentValues.put(BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID, value);
        return this;
    }

}
