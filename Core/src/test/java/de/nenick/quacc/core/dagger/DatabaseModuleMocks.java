package de.nenick.quacc.core.dagger;

import android.content.Context;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc.class, GetAccountsUc.class},
        complete = false
)
public class DatabaseModuleMocks implements DatabaseModuleBase {

    @Mock
    public AccountingTypeRepository accountingTypeRepository;

    @Mock
    public AccountRepository accountRepository;

    public DatabaseModuleMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Override
    @Provides
    public AccountingTypeRepository provideAccountingTypeRepository() {
        return accountingTypeRepository;
    }

    @Override
    @Provides
    public AccountRepository provideAccountRepository(@ForApplication Context context) {
        return accountRepository;
    }
}
