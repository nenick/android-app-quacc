package de.nenick.quacc.core.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.accounting.interval.AccountingInterval;
import de.nenick.quacc.database.category.CategoryDb;

@EBean
public class CreateCategoryFunction {

    @Bean
    CategoryDb categoryDb;

    public void apply(String section, String name, String interval, String type) {
        createSectionGroupIfNotExist(section);
        createCategory(section, name, interval, type, 1);
    }

    private void createCategory(String section, String name, String interval, String type, int level) {
        categoryDb.insert(section, name, interval, type, level);
    }

    private void createSectionGroupIfNotExist(String section) {
        if(!categoryDb.existSection(section)) {
            categoryDb.insert(section, section, AccountingInterval.all.name(), AccountingInterval.all.name(), 0);
        }
    }
}
