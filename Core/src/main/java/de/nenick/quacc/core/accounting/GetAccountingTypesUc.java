package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.provider.accounting.AccountingType;

@EBean
public class GetAccountingTypesUc {

    public CharSequence[] apply() {
        AccountingType[] values = AccountingType.values();
        CharSequence[] types = new CharSequence[values.length];
        for (int i = 0; i < values.length; i++) {
            types[i] = values[i].name();
        }
        return types;
    }
}
