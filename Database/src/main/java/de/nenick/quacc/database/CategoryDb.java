package de.nenick.quacc.database;

import android.content.Context;
import android.database.Cursor;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.category.CategoryContentValues;
import de.nenick.quacc.database.provider.category.CategoryCursor;
import de.nenick.quacc.database.provider.category.CategorySelection;

@EBean
public class CategoryDb {

    public static final String sortBySectionAndName = CategoryColumns.SECTION + " ASC, " + CategoryColumns.LEVEL + " ASC, " + CategoryColumns.NAME + " ASC";

    @RootContext
    Context context;

    public long getIdByName(String categoryName) {
        CategoryCursor query = new CategorySelection().name(categoryName).query(context.getContentResolver(), new String[]{CategoryColumns._ID});
        query.moveToNext();

        Cursor query1 = context.getContentResolver().query(CategoryColumns.CONTENT_URI, null, null, null, null);
        query1.moveToNext();

        long id = query.getId();
        query.close();
        return id;
    }

    public CategoryCursor getAll() {
        return new CategorySelection().query(context.getContentResolver());
    }

    public void insert(String name) {
        new CategoryContentValues().putName(name).insert(context.getContentResolver());
    }

    public void insert(String section, String name, String interval, String type) {
        new CategoryContentValues().putName(name).putInterval(interval).putSection(section).putType(type).insert(context.getContentResolver());
    }

    public CategoryCursor getAllFor(String[] intervals, String[] types, String sortOrder) {
        CategorySelection where = new CategorySelection();
        return where.interval(intervals).and().type(types)
                .query(context.getContentResolver(), null, sortOrder);
    }
}
