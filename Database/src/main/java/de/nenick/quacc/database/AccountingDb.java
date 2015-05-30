package de.nenick.quacc.database;

import android.content.Context;

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

    public void insert(long accountId, String accountingType, String accountingInterval, long accountingCategoryId, Date date, String comment, int value) {
        new AccountingContentValues()
                .putAccountId(accountId)
                .putType(accountingType)
                .putInterval(accountingInterval)
                .putCategoryId(accountingCategoryId)
                .putDate(date)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
    }

    public AccountingCursor getAll() {
        return new AccountingSelection().query(context.getContentResolver());
    }

    public AccountingCursor getAllBetween(Date startDate, Date endDate) {
        AccountingSelection where = new AccountingSelection();
        where.dateAfter(startDate).and().dateBefore(endDate);
        return where.query(context.getContentResolver(), null, AccountingColumns.DATE);
    }
}
