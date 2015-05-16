package de.nenick.quacc.accounting.create.functions;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.provider.accounting.AccountingInterval;

@EBean
public class GetAccountingIntervalsFunction {

    public CharSequence[] apply() {
        AccountingInterval[] values = AccountingInterval.values();
        CharSequence[] types = new CharSequence[values.length];
        for (int i = 0; i < values.length; i++) {
            types[i] = values[i].name();
        }
        return types;
    }
}
