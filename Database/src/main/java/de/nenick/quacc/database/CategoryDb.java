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

    public CategoryCursor getAllFor(String accountingType, String accountingInterval) {
        return new CategorySelection().type(accountingType).and().interval(accountingInterval).or().interval(AccountingInterval.all.name()).or().type(AccountingType.all.name()).query(context.getContentResolver());
    }
}
