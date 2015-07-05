package de.nenick.quacc.database.interval;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;
import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.database.provider.interval.IntervalSelection;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingContentValues;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingCursor;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingSelection;

@EBean
public class IntervalAccountingDb {

    @RootContext
    Context context;

    public long insert(long intervalId, long accountingId) {
        Uri uri = new IntervalAccountingContentValues()
                .putIntervalId(intervalId)
                .putAccountingId(accountingId)
                .insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public IntervalAccountingCursor getAll() {
        return new IntervalAccountingSelection().query(context.getContentResolver());
    }

    public IntervalAccountingCursor getById(long id) {
        return new IntervalAccountingSelection().id(id).query(context.getContentResolver());
    }
}
