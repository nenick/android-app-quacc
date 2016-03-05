package de.nenick.quacc.database.provider.category;

import java.util.Date;

import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code category} table.
 */
public class CategoryCursor extends AbstractCursor implements CategoryModel {
    public CategoryCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Primary key.
     */
    public long getId() {
        Long res = getLongOrNull(CategoryColumns._ID);
        if (res == null)
            throw new NullPointerException("The value of '_id' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Name of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getName() {
        String res = getStringOrNull(CategoryColumns.NAME);
        if (res == null)
            throw new NullPointerException("The value of 'name' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Main group of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getSection() {
        String res = getStringOrNull(CategoryColumns.SECTION);
        if (res == null)
            throw new NullPointerException("The value of 'section' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Possible booking interval for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getInterval() {
        String res = getStringOrNull(CategoryColumns.INTERVAL);
        if (res == null)
            throw new NullPointerException("The value of 'interval' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Possible booking direction for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    public String getDirection() {
        String res = getStringOrNull(CategoryColumns.DIRECTION);
        if (res == null)
            throw new NullPointerException("The value of 'direction' in the database was null, which is not allowed according to the model definition");
        return res;
    }

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public int getLevel() {
        Integer res = getIntegerOrNull(CategoryColumns.LEVEL);
        if (res == null)
            throw new NullPointerException("The value of 'level' in the database was null, which is not allowed according to the model definition");
        return res;
    }
}
