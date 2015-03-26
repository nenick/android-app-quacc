package de.nenick.quacc.database;

import de.nenick.quacc.database.provider.accounting.AccountingType;

public class AccountingTypeRepository {

    public String[] getAccountingTypes() {
        AccountingType[] values = AccountingType.values();
        String[] types = new String[values.length];
        for(int i = 0; i < values.length; i++) {
            types[i] = values[i].name();
        }
        return types;
    }
}
