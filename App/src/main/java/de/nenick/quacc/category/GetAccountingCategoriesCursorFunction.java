package de.nenick.quacc.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.category.CategoryDb;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class GetAccountingCategoriesCursorFunction {

    @Bean
    CategoryDb categoryDb;

    public CategoryCursor apply() {
        return categoryDb.getAll();
    }
}
