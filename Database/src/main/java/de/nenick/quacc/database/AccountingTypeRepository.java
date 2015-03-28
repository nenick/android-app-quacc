package de.nenick.quacc.database;

import de.nenick.quacc.database.provider.accounting.AccountingType;

public class AccountingTypeRepository {

    public CharSequence[] getAccountingTypes() {
        AccountingType[] values = AccountingType.values();
        CharSequence[] types = new CharSequence[values.length];
        for (int i = 0; i < values.length; i++) {
            types[i] = values[i].name();
        }
        return types;
    }
}
