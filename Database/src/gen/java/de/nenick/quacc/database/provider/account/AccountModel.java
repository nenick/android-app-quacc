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
     * The name of the account set by the user.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * The base to calculate the current amount of money. Values are stored in 100 cent.
     */
    int getInitialvalue();
}
