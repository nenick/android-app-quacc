package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.Date;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.accounting.AccountingContentValues;
import de.nenick.quacc.database.provider.accounting.AccountingInterval;
import de.nenick.quacc.database.provider.accounting.AccountingType;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class AddNewAccountingUc {

    @RootContext
    Context context;

    public void apply(String account, String accountingType, String accountingInterval, String accountingCategory, Date date, int value, String comment) {
        long accountId = getAccountId(account);
        long accountingCategoryId = getAccountCategoryId(accountingCategory);

        new AccountingContentValues()
                .putAccountId(accountId)
                .putAccountingType(AccountingType.valueOf(accountingType))
                .putAccountingInterval(AccountingInterval.valueOf(accountingInterval))
                .putAccountingCategoryId(accountingCategoryId)
                .putAccountingDate(date)
                .putComment(comment)
                .putValue(value).insert(context.getContentResolver());
    }

    private long getAccountCategoryId(String accountingCategory) {
        AccountingCategoryCursor query = new AccountingCategorySelection().name(accountingCategory).query(context.getContentResolver());
        query.moveToNext();
        long id = query.getId();
        query.close();
        return id;
    }

    private long getAccountId(String account) {
        AccountCursor query = new AccountSelection().name(account).query(context.getContentResolver());
        query.moveToNext();
        long id = query.getId();
        query.close();
        return id;
    }
}
