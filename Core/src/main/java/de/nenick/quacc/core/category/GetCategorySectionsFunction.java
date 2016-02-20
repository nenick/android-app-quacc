package de.nenick.quacc.core.category;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.category.CategoryRepository;
import de.nenick.quacc.database.category.CategorySpecAllSections;
import de.nenick.quacc.database.provider.category.CategoryCursor;

@EBean
public class GetCategorySectionsFunction {

    @Bean
    CategoryRepository categoryRepository;

    public CategoryCursor apply() {
        return categoryRepository.query(new CategorySpecAllSections());
    }
}
