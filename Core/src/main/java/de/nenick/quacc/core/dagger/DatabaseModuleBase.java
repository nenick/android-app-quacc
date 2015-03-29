package de.nenick.quacc.core.dagger;


import android.content.Context;

import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountingCategoryRepository;
import de.nenick.quacc.database.AccountingIntervalRepository;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.AccountingTypeRepository;

public interface DatabaseModuleBase {

    AccountingTypeRepository provideAccountingTypeRepository();

    AccountRepository provideAccountRepository(Context context);

    AccountingRepository provideAccountingRepository(Context context);

    AccountingCategoryRepository provideAccountingCategoryRepository(Context context);

    AccountingIntervalRepository provideAccountingIntervalRepository();
}
