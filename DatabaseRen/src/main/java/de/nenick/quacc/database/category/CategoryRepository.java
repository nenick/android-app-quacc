package de.nenick.quacc.database.category;

import android.net.Uri;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;

@EBean
public class CategoryRepository extends BaseRepository<CategoryContentValues, CategorySpec, CategoryCursor> {

    @Override
    public void update(CategoryContentValues values, CategorySpec specification) {
        CategorySelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public CategoryCursor query(CategorySpec specification) {
        CategorySelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), null, specification.sortBy());
    }

    @Override
    public Uri uri() {
        return CategoryColumns.CONTENT_URI;
    }
}
