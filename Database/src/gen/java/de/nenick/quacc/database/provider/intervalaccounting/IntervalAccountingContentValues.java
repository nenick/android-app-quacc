package de.nenick.quacc.database.provider.intervalaccounting;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code interval_accounting} table.
 */
public class IntervalAccountingContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return IntervalAccountingColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable IntervalAccountingSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public IntervalAccountingContentValues putIntervalId(long value) {
        mContentValues.put(IntervalAccountingColumns.INTERVAL_ID, value);
        return this;
    }


    public IntervalAccountingContentValues putAccountingId(long value) {
        mContentValues.put(IntervalAccountingColumns.ACCOUNTING_ID, value);
        return this;
    }

}
