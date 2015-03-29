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

    AccountRepository provideAccountRepository(@ForApplication Context context);

    AccountingRepository provideAccountingRepository(@ForApplication Context context);

    AccountingCategoryRepository provideAccountingCategoryRepository(@ForApplication Context context);

    AccountingIntervalRepository provideAccountingIntervalRepository();
}
