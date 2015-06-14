package de.nenick.quacc.view.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.CategoryDb;

@EBean
public class CreateCategoryFunction {

    @Bean
    CategoryDb categoryDb;

    public void apply(String name) {
        categoryDb.insert(name);
    }
}
