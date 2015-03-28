package de.nenick.quacc.core.dagger;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc.class},
        complete = false
)
public class DatabaseModuleMocks implements DatabaseModuleBase {

    @Mock
    public AccountingTypeRepository accountingTypeRepository;

    public DatabaseModuleMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Provides
    @Override
    public AccountingTypeRepository provideAccountingTypeRepository() {
        return accountingTypeRepository;
    }
}
