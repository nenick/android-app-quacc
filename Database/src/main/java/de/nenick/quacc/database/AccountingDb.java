package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingContentValues;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.accounting.AccountingType;

@EBean
public class AccountingDb {

    @RootContext
    Context context;

    public void insert(long accountId, String accountingType, String accountingInterval, long accountingCategoryId, Date date, String comment, int value) {
        new AccountingContentValues()
                .putAccountId(accountId)
                .putAccountingType(AccountingType.valueOf(accountingType))
                .putAccountingInterval(AccountingInterval.valueOf(accountingInterval))
                .putAccountingCategoryId(accountingCategoryId)
                .putAccountingDate(date)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
    }

    public AccountingCursor getAll() {
        return new AccountingSelection().query(context.getContentResolver());
    }
}
