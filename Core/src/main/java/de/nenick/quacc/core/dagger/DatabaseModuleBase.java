package de.nenick.quacc.core.dagger;


import de.nenick.quacc.database.AccountingTypeRepository;

public interface DatabaseModuleBase {

    AccountingTypeRepository provideAccountingTypeRepository();

}
