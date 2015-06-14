package de.nenick.quacc.database;

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
        where.accountId(accountId).and().dateAfter(startDate).and().dateBefore(endDate);
        return where.query(context.getContentResolver(), null, AccountingColumns.DATE);
    }
}
