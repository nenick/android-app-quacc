package de.nenick.quacc.database;

import de.nenick.quacc.database.provider.accounting.AccountingInterval;

public class AccountingIntervalRepository {

    public CharSequence[] getAccountingIntervals() {
        AccountingInterval[] values = AccountingInterval.values();
        CharSequence[] types = new CharSequence[values.length];
        for (int i = 0; i < values.length; i++) {
            types[i] = values[i].name();
        }
        return types;
    }
}
