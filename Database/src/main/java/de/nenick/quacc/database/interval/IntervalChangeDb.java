package de.nenick.quacc.database.interval;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeColumns;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeContentValues;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeCursor;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeSelection;

@EBean
public class IntervalChangeDb {

    private final String[] ALL_COLUMN_AND_FROM_JOIN = ObjectArrays.concat(IntervalChangeColumns.ALL_COLUMNS, IntervalColumns.ALL_COLUMNS, String.class);

    @RootContext
    Context context;

    public long insert(long intervalId, Date date, String change, String comment, int value) {
        Uri uri = new IntervalChangeContentValues()
                .putIntervalId(intervalId)
                .putChange(change)
                .putComment(comment)
                .putDate(date)
                .putValue(value)
                .insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public IntervalChangeCursor getAll() {
        return new IntervalChangeSelection().query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public IntervalChangeCursor getAllForInterval(long intervalId) {
        return new IntervalChangeSelection().intervalId(intervalId).query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public IntervalChangeCursor getById(long id) {
        return new IntervalChangeSelection().id(id).query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }
}
