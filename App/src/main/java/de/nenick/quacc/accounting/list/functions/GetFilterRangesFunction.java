package de.nenick.quacc.accounting.list.functions;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.nenick.quacc.accounting.list.FilterRange;
import de.nenick.quacc.database.AccountingType;

@EBean
public class GetFilterRangesFunction {

    public Collection<String> apply() {
        FilterRange[] values = FilterRange.values();
        List<String> valueStrings = new ArrayList<>();
        for (FilterRange value : values) {
            valueStrings.add(value.name());
        }
        return valueStrings;
    }
}
