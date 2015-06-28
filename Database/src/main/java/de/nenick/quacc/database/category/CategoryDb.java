package de.nenick.quacc.database.category;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
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

    public CategoryCursor getAllSections() {
        return new CategorySelection().groupBy(CategoryColumns.SECTION).query(context.getContentResolver());
    }

    public long insert(String section, String name, String interval, String type, int level) {
        Uri uri = new CategoryContentValues().putName(name).putInterval(interval).putSection(section).putType(type).putLevel(level).insert(context.getContentResolver());
        return ContentUris.parseId(uri);
    }

    public void insertWithId(long id, String section, String name, String interval, String type, int level) {
        new CategoryContentValues().putName(name).putInterval(interval).putSection(section).putType(type).putLevel(level).insert(context.getContentResolver());
    }

    public CategoryCursor getAllFor(String[] intervals, String[] types, String sortOrder) {
        CategorySelection where = new CategorySelection();
        return where.interval(intervals).and().type(types)
                .query(context.getContentResolver(), null, sortOrder);
    }

    public boolean existSection(String section) {
        CategoryCursor query = new CategorySelection().section(section).query(context.getContentResolver());
        boolean exist = query.getCount() > 0;
        query.close();
        return exist;
    }

    public void deleteAll() {
        new CategorySelection().delete(context.getContentResolver());
    }
}
