package de.nenick.quacc.database.category;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.base.Repository;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;

@EBean
public class CategoryRepository extends Repository<CategoryContentValues, CategorySpec, CategorySelection, CategoryCursor> {

    @Override
    public void update(CategoryContentValues values, CategorySpec specification) {
        CategorySelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public CategoryCursor query(QuerySpecification<CategorySelection> specification) {
        CategorySelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), null, specification.sortBy());
    }
}
