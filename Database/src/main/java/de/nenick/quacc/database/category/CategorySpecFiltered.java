package de.nenick.quacc.database.category;

import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategorySelection;

public class CategorySpecFiltered extends CategorySpec {


    private final String[] intervals;
    private final String[] direction;

    public CategorySpecFiltered(String[] intervals, String[] direction) {
        this.intervals = intervals;
        this.direction = direction;
    }

    @Override
    public CategorySelection toSelection() {
        return new CategorySelection().interval(intervals).and().direction(direction);
    }

    @Nullable
    @Override
    public String sortBy() {
        return CategoryColumns.SECTION + " ASC, " + CategoryColumns.LEVEL + " ASC, " + CategoryColumns.NAME + " ASC";
    }
}
