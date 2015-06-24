package de.nenick.quacc.database.interval;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.database.provider.interval.IntervalContentValues;
import de.nenick.quacc.database.provider.interval.IntervalCursor;
import de.nenick.quacc.database.provider.interval.IntervalSelection;

@EBean
public class IntervalDb {

    private final String[] ALL_COLUMN_AND_FROM_JOIN = ObjectArrays.concat(ObjectArrays.concat(IntervalColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);
    public static Date NO_DATE_GIVEN = new Date(0);

    @RootContext
    Context context;

    public long insert(long accountId, String accountingType, String accountingInterval, long accountingCategoryId, Date startDate, Date endDate, String comment, int value) {
        Uri uri = new IntervalContentValues()
                .putAccountId(accountId)
                .putType(accountingType)
                .putInterval(accountingInterval)
                .putCategoryId(accountingCategoryId)
                .putDateStart(startDate)
                .putDateEnd(endDate)
                .putComment(comment)
                .putValue(value)
                .putDateLast(NO_DATE_GIVEN)
                .putDateUpdatedUntil(NO_DATE_GIVEN)
                .insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public void updatedUntil(long intervalId, Date lastDate, Date updatedUntil) {
        new IntervalContentValues()
                .putDateLast(lastDate)
                .putDateUpdatedUntil(updatedUntil)
                .update(context.getContentResolver(), new IntervalSelection().id(intervalId));
    }

    public IntervalCursor getAll() {
        return new IntervalSelection().query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }

    public IntervalCursor getAllBetween(long accountId, Date startDate, Date endDate) {
        IntervalSelection where = new IntervalSelection();
        where.accountId(accountId)
                .and().dateStartAfter(startDate)
                .and().dateEndBefore(endDate).or().dateEnd(NO_DATE_GIVEN);
        return where.query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN, AccountingColumns.DATE);
    }

    public IntervalCursor getById(long intervalId) {
        return new IntervalSelection().id(intervalId).query(context.getContentResolver());
    }

    public IntervalCursor getAllForAccountNotUpdatedUntil(long accountId, Date updatedUntil) {
        return new IntervalSelection().accountId(accountId).and().dateUpdatedUntilBefore(updatedUntil).query(context.getContentResolver(), ALL_COLUMN_AND_FROM_JOIN);
    }
}
