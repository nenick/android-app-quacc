package de.nenick.quacc.core.accounting.interval;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class GetAccountingIntervalsFunction {

    public Collection<String> apply() {
        AccountingInterval[] values = AccountingInterval.values();
        List<String> types = new ArrayList<>();
        for (AccountingInterval value : values) {
            if(value == AccountingInterval.all) continue;
            types.add(value.name());
        }
        return types;
    }
}
