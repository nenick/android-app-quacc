package de.nenick.quacc.core.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc_;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc_;
import de.nenick.quacc.core.accounting.GetAccountingListUc_;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;
import de.nenick.quacc.core.accounting.GetAccountsUc_;
import de.nenick.quacc.dagger.ForApplication;
import de.nenick.quacc.database.AccountRepository;
import de.nenick.quacc.database.AccountRepository_;
import de.nenick.quacc.database.AccountingCategoryRepository;
import de.nenick.quacc.database.AccountingCategoryRepository_;
import de.nenick.quacc.database.AccountingIntervalRepository;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.AccountingRepository_;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc_.class, GetAccountsUc_.class, GetAccountingCategoriesUc_.class, GetAccountingIntervalsUc_.class, GetAccountingListUc_.class},
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

    @Override
    @Provides
    public AccountingRepository provideAccountingRepository(@ForApplication Context context) {
        return AccountingRepository_.getInstance_(context);
    }

    @Override
    @Provides
    public AccountingCategoryRepository provideAccountingCategoryRepository(@ForApplication Context context) {
        return AccountingCategoryRepository_.getInstance_(context);
    }

    @Override
    @Provides
    public AccountingIntervalRepository provideAccountingIntervalRepository() {
        return new AccountingIntervalRepository();
    }
}
