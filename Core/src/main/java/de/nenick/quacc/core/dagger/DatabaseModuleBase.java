package de.nenick.quacc.core.dagger;


import android.content.Context;

import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountingTypeRepository;

public interface DatabaseModuleBase {

    AccountingTypeRepository provideAccountingTypeRepository();

    AccountRepository provideAccountRepository(@ForApplication Context context);
}
