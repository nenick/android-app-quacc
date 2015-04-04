package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategorySelection;

@EBean
public class GetAccountingCategoriesUc {

    @RootContext
    Context context;

    public CharSequence[] apply() {
        AccountingCategoryCursor accountingCategories = new AccountingCategorySelection().query(context.getContentResolver());
        String[] values = new String[accountingCategories.getCount()];
        for (int i = 0; i < accountingCategories.getCount(); i++) {
            accountingCategories.moveToNext();
            values[i] = accountingCategories.getName();
        }
        return values;
    }
}
