package de.nenick.quacc.accounting.type;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
