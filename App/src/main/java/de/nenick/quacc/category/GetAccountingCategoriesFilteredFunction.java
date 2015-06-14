package de.nenick.quacc.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.AccountingInterval;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.database.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class GetAccountingCategoriesFilteredFunction {

    @Bean
    CategoryDb categoryDb;

    public CategoryCursor apply(String interval, String type) {
        String[] intervals = {interval, AccountingInterval.all.name()};
        String[] types = {type, AccountingType.all.name()};
        return categoryDb.getAllFor(intervals, types, CategoryDb.sortBySectionAndName);
    }
}
