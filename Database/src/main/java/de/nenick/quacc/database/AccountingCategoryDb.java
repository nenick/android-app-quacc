package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class AccountingCategoryDb {

    @RootContext
    Context context;

    public long getIdByName(String categoryName) {
        AccountingCategoryCursor query = new AccountingCategorySelection().name(categoryName).query(context.getContentResolver());
        query.moveToNext();
        long id = query.getId();
        query.close();
        return id;
    }

    public AccountingCategoryCursor getAll() {
        return new AccountingCategorySelection().query(context.getContentResolver());
    }
}
