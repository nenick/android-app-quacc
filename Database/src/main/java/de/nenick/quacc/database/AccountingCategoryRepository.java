package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.database.provider.account.AccountSelection;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class AccountingCategoryRepository {

    @RootContext
    Context context;

    public AccountingCategoryCursor getAccountingCategories() {
        return new AccountingCategorySelection().query(context.getContentResolver());
    }

    public long getIdFor(String account) {
        AccountingCategoryCursor query = new AccountingCategorySelection().name(account).query(context.getContentResolver());
        query.moveToNext();
        return query.getId();
    }
}
