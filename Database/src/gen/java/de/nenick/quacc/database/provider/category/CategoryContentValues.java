package de.nenick.quacc.database.provider.category;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code category} table.
 */
public class CategoryContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return CategoryColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, @Nullable CategorySelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    /**
     * Name of the category.
     */
    public CategoryContentValues putName(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("name must not be null");
        mContentValues.put(CategoryColumns.NAME, value);
        return this;
    }


    /**
     * Main group of the category.
     */
    public CategoryContentValues putSection(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("section must not be null");
        mContentValues.put(CategoryColumns.SECTION, value);
        return this;
    }


    /**
     * Possible booking interval for this category.
     */
    public CategoryContentValues putInterval(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("interval must not be null");
        mContentValues.put(CategoryColumns.INTERVAL, value);
        return this;
    }


    /**
     * Possible booking direction for this category.
     */
    public CategoryContentValues putDirection(@NonNull String value) {
        if (value == null) throw new IllegalArgumentException("direction must not be null");
        mContentValues.put(CategoryColumns.DIRECTION, value);
        return this;
    }


    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public CategoryContentValues putLevel(int value) {
        mContentValues.put(CategoryColumns.LEVEL, value);
        return this;
    }

}
