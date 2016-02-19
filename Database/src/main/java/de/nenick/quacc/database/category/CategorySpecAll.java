package de.nenick.quacc.database.category;

import de.nenick.quacc.database.provider.category.CategorySelection;

public class CategorySpecAll extends CategorySpec {

    @Override
    public CategorySelection toSelection() {
        return new CategorySelection();
    }
}
