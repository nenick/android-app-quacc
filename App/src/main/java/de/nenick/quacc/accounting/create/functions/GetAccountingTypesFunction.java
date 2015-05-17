package de.nenick.quacc.accounting.create.functions;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.nenick.quacc.database.AccountingType;

@EBean
public class GetAccountingTypesFunction {

    public Collection<String> apply() {
        AccountingType[] values = AccountingType.values();
        List<String> types = new ArrayList<>();
        for (AccountingType value : values) {
            if(value == AccountingType.all) continue;
            types.add(value.name());
        }
        return types;
    }
}
