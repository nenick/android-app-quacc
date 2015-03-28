package de.nenick.quacc.core.dagger;

import dagger.Module;
import dagger.Provides;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;
import de.nenick.quacc.database.AccountingTypeRepository;

@Module(
        injects = {GetAccountingTypesUc_.class},
        complete = false,
        library = true
)
public class DatabaseModule implements DatabaseModuleBase {

        @Provides
        @Override
        public AccountingTypeRepository provideAccountingTypeRepository() {
                return new AccountingTypeRepository();
        }
}
