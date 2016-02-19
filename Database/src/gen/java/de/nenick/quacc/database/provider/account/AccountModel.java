package de.nenick.quacc.database.provider.account;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An unit where user store money e.g. bank and wallet
 */
public interface AccountModel extends BaseModel {

    /**
     * The name of the account.
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
     */
    int getInitialvalue();
}
