package de.nenick.quacc.accounting.create.functions;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class GetAccountingCategoriesFunction {

    @Bean
    CategoryDb categoryDb;
/*
    public CharSequence[] apply() {
        CategoryCursor accountingCategories = categoryDb.getAll();
        String[] values = new String[accountingCategories.getCount()];
        for (int i = 0; i < accountingCategories.getCount(); i++) {
            accountingCategories.moveToNext();
            values[i] = accountingCategories.getName();
        }
        accountingCategories.close();
        return values;
    }
*/
    public CharSequence[] apply(String accountingType, String accountingInterval) {
        CategoryCursor accountingCategories =  categoryDb.getAllFor(accountingType, accountingInterval);
        String[] values = new String[accountingCategories.getCount()];
        for (int i = 0; i < accountingCategories.getCount(); i++) {
            accountingCategories.moveToNext();
            values[i] = accountingCategories.getName();
        }
        accountingCategories.close();
        return values;
    }
}
