package de.nenick.quacc.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.core.accounting.type.AccountingType;
import de.nenick.quacc.database.category.CategoryDb;
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
