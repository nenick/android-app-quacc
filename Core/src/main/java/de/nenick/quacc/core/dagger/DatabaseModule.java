package de.nenick.quacc.core.dagger;

import dagger.Module;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc_;

@Module(
        injects = {GetAccountingTypesUc_.class},
        complete = false
)
public class DatabaseModule implements DatabaseModuleBase {


}
