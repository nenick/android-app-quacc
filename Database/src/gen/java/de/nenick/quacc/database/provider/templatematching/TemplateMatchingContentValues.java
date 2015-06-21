package de.nenick.quacc.database.provider.templatematching;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code template_matching} table.
 */
public class TemplateMatchingContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return TemplateMatchingColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable TemplateMatchingSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public TemplateMatchingContentValues putText(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("text must not be null");
        mContentValues.put(TemplateMatchingColumns.TEXT, value);
        return this;
    }


    public TemplateMatchingContentValues putAccountingTemplateId(long value) {
        mContentValues.put(TemplateMatchingColumns.ACCOUNTING_TEMPLATE_ID, value);
        return this;
    }

}
