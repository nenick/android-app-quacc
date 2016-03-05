package de.nenick.quacc.database.category;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategorySelection;

public class CategorySpecAllSections extends CategorySpec {

    @Override
    public CategorySelection toSelection() {
        return new CategorySelection().groupBy(CategoryColumns.SECTION);
    }
}
