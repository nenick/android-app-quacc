package de.nenick.quacc.core.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;
import de.nenick.quacc.core.accounting.GetAccountsUc_;
import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountRepository_;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc_.class, GetAccountsUc_.class},
        complete = false,
        library = true
)
public class DatabaseModule implements DatabaseModuleBase {

    @Override
    @Provides
    public AccountingTypeRepository provideAccountingTypeRepository() {
        return new AccountingTypeRepository();
    }

    @Override
    @Provides
    public AccountRepository provideAccountRepository(@ForApplication Context context) {
        return AccountRepository_.getInstance_(context);
    }
}
