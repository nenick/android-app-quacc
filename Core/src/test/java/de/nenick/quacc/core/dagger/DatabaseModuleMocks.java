package de.nenick.quacc.core.dagger;

import android.content.Context;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountingCategoryRepository;
import de.nenick.quacc.database.AccountingIntervalRepository;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc.class, GetAccountsUc.class, GetAccountingCategoriesUc.class, GetAccountingIntervalsUc.class, GetAccountingListUc.class},
        complete = false
)
public class DatabaseModuleMocks implements DatabaseModuleBase {

    @Mock
    public AccountingTypeRepository accountingTypeRepository;

    @Mock
    public AccountRepository accountRepository;

    @Mock
    public AccountingRepository accountingRepository;

    @Mock
    public AccountingCategoryRepository accountingCategoryRepository;

    @Mock
    public AccountingIntervalRepository accountingIntervalRepository;

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

    @Override
    @Provides
    public AccountingRepository provideAccountingRepository(@ForApplication Context context) {
        return accountingRepository;
    }

    @Override
    @Provides
    public AccountingCategoryRepository provideAccountingCategoryRepository(@ForApplication Context context) {
        return accountingCategoryRepository;
    }

    @Override
    @Provides
    public AccountingIntervalRepository provideAccountingIntervalRepository() {
        return accountingIntervalRepository;
    }
}
