package de.nenick.quacc.database.category;

import de.nenick.quacc.database.provider.category.CategorySelection;

public class CategorySpecBySections extends CategorySpec {

    private String section;

    public CategorySpecBySections(String section) {
        this.section = section;
    }

    @Override
    public CategorySelection toSelection() {
        return new CategorySelection().section(section);
    }
}
