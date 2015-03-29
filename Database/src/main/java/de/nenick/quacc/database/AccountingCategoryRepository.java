package de.nenick.quacc.database;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class AccountingCategoryRepository {

    @RootContext
    Context context;

    public AccountingCategoryCursor getAccountingCategories() {
        return new AccountingCategorySelection().query(context.getContentResolver());
    }
}
