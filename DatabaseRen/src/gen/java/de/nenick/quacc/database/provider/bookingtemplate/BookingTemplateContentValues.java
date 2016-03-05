package de.nenick.quacc.database.provider.bookingtemplate;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code booking_template} table.
 */
public class BookingTemplateContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return BookingTemplateColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable BookingTemplateSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Referenced account.
     */
    public BookingTemplateContentValues putAccountId(long value) {
        mContentValues.put(BookingTemplateColumns.ACCOUNT_ID, value);
        return this;
    }


    /**
     * Referenced category.
     */
    public BookingTemplateContentValues putCategoryId(long value) {
        mContentValues.put(BookingTemplateColumns.CATEGORY_ID, value);
        return this;
    }


    /**
     * Optional description for this template.
     */
    public BookingTemplateContentValues putComment(@Nullable String value) {
        mContentValues.put(BookingTemplateColumns.COMMENT, value);
        return this;
    }

    public BookingTemplateContentValues putCommentNull() {
        mContentValues.putNull(BookingTemplateColumns.COMMENT);
        return this;
    }

    /**
     * Possible booking interval for this template.
     */
    public BookingTemplateContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(BookingTemplateColumns.INTERVAL, value);
        return this;
    }


    /**
     * Possible booking direction for this template.
     */
    public BookingTemplateContentValues putDirection(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("direction must not be null");
        mContentValues.put(BookingTemplateColumns.DIRECTION, value);
        return this;
    }

}
