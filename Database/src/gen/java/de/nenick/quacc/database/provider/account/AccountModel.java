package de.nenick.quacc.database.provider.account;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Consolidate the accounting.
 */
public interface AccountModel extends BaseModel {

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * Short description
     * Can be {@code null}.
     */
    @Nullable
    String getDescription();
}
