package de.nenick.quacc.database;

import android.content.Context;
import android.database.Cursor;

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

    public void insert(long intervalId, long accountingId) {
        new IntervalAccountingContentValues()
                .putIntervalId(intervalId)
                .putAccountingId(accountingId)
                .insert(context.getContentResolver());
    }

    public IntervalAccountingCursor getAll() {
        return new IntervalAccountingSelection().query(context.getContentResolver());
    }
}
