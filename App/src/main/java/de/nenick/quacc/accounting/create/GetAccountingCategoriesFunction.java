package de.nenick.quacc.accounting.create;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.AccountingCategoryDb;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class GetAccountingCategoriesFunction {

    @RootContext
    Context context;

    @Bean
    AccountingCategoryDb accountingCategoryDb;

    public CharSequence[] apply() {
        AccountingCategoryCursor accountingCategories = accountingCategoryDb.getAll();
        String[] values = new String[accountingCategories.getCount()];
        for (int i = 0; i < accountingCategories.getCount(); i++) {
            accountingCategories.moveToNext();
            values[i] = accountingCategories.getName();
        }
        accountingCategories.close();
        return values;
    }
}
