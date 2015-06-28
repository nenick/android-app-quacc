package de.nenick.quacc.database.accounting;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accounting.AccountingContentValues;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class AccountingDb {

    @RootContext
    Context context;

    public long insert(long accountId, String accountingType, String accountingInterval, long accountingCategoryId, Date date, String comment, int value) {
        Uri uri = new AccountingContentValues()
                .putAccountId(accountId)
                .putType(accountingType)
                .putInterval(accountingInterval)
                .putCategoryId(accountingCategoryId)
                .putDate(date)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public AccountingCursor getAll() {
        return new AccountingSelection().query(context.getContentResolver());
    }

    public AccountingCursor getAllBetween(long accountId, Date startDate, Date endDate) {
        AccountingSelection where = new AccountingSelection();
        where.accountId(accountId)
                .and().dateAfter(startDate)
                .and().dateBefore(endDate);
        AccountingCursor query = where.query(context.getContentResolver(), null, AccountingColumns.DATE);
        query.setNotificationUri(context.getContentResolver(), AccountingColumns.CONTENT_URI);
        return query;
    }

    public AccountingCursor getGroupsBetween(long accountId, Date startDate, Date endDate) {

        String[] projection = {CategoryColumns.NAME, AccountingColumns.CATEGORY_ID, AccountingColumns.TYPE, AccountingColumns._ID, AccountingColumns._ID, "MIN(" + AccountingColumns.DATE +") AS minDate", "MAX(" + AccountingColumns.DATE +") AS " + AccountingColumns.DATE, "SUM(" + AccountingColumns.VALUE + ") AS " + AccountingColumns.VALUE};

        AccountingSelection where = new AccountingSelection();
        where.accountId(accountId)
                .and().dateAfter(startDate)
                .and().dateBefore(endDate)
                .groupBy(AccountingColumns.CATEGORY_ID + " , " + AccountingColumns.TYPE);
        AccountingCursor query = where.query(context.getContentResolver(), projection, AccountingColumns.DATE);
        query.setNotificationUri(context.getContentResolver(), AccountingColumns.CONTENT_URI);
        return query;
    }

    public AccountingCursor getForGroupBetween(long accountId, long categoryId, String type, Date startDate, Date endDate) {
        AccountingSelection where = new AccountingSelection();
        where.accountId(accountId)
                .and().dateAfter(startDate)
                .and().dateBefore(endDate)
                .and().categoryId(categoryId)
                .and().type(type);
        AccountingCursor query = where.query(context.getContentResolver(), null, AccountingColumns.DATE);
        query.setNotificationUri(context.getContentResolver(), AccountingColumns.CONTENT_URI);
        return query;
    }

    public AccountingCursor getById(long id) {
        return new AccountingSelection().id(id).query(context.getContentResolver());
    }

    public void deleteById(long accountingId) {
        new AccountingSelection().id(accountingId).delete(context.getContentResolver());
        context.getContentResolver().notifyChange(AccountingColumns.CONTENT_URI, null);
    }
}
