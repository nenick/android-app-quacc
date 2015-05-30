package de.nenick.quacc.database.provider.category;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Data model for the {@code category} table.
 */
public interface CategoryModel extends BaseModel {

    /**
     * Get the {@code name} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Get the {@code section} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getSection();

    /**
     * Get the {@code interval} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getInterval();

    /**
     * Get the {@code type} value.
     * Cannot be {@code null}.
     */
    @NonNull
    String getType();

    /**
     * Get the {@code level} value.
     */
    int getLevel();
}
