package de.nenick.quacc.database.provider.category;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Group for booking entry with main and sub category
 */
public interface CategoryModel extends BaseModel {

    /**
     * Name of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Main group of the category.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSection();

    /**
     * Possible booking interval for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Possible booking direction for this category.
     * Cannot be {@code null}.
     */
    @NonNull
    String getDirection();

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    int getLevel();
}
