package de.nenick.quacc.database.provider.accountinginterval;

import de.nenick.quacc.database.provider.base.BaseModel;

import java.util.Date;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Available intervals for an accounting
 */
public interface AccountingIntervalModel extends BaseModel {

    /**
     * Name
     * Cannot be {@code null}.
     */
    @NonNull
    String getName();
}
