package de.nenick.quacc.database.provider.accountingcategory;

import android.support.annotation.NonNull;

import de.nenick.quacc.database.provider.base.BaseModel;

/**
 * Consolidate accounting with a categorization.
 */
public interface AccountingCategoryModel extends BaseModel {

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();
}
