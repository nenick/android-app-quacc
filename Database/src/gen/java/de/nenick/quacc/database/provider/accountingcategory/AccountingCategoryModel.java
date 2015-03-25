package de.nenick.quacc.database.provider.accountingcategory;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

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
