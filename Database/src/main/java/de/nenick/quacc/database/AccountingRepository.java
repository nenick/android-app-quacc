package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingContentValues;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;
import de.nenick.quacc.database.provider.accounting.AccountingType;

@EBean
public class AccountingRepository {

    @RootContext
    Context context;

    public AccountingCursor getAccountings() {
        return new AccountingSelection().query(context.getContentResolver());
    }

    public void insertAccounting(long accountId, AccountingType accountingType, AccountingInterval accountingInterval, long accountingCategoryId, Date date, int value, String comment) {
        new AccountingContentValues()
                .putAccountId(accountId)
                .putAccountingType(accountingType)
                .putAccountingInterval(accountingInterval)
                .putAccountingCategoryId(accountingCategoryId)
                .putAccountingDate(date)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
    }
}
