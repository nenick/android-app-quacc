package de.nenick.quacc.accounting_overview.filter;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class GetFilterRangesAsStringsFunction {

    public Collection<String> apply() {
        FilterRange[] values = FilterRange.values();
        List<String> valueStrings = new ArrayList<>();
        for (FilterRange value : values) {
            valueStrings.add(value.name());
        }
        return valueStrings;
    }
}
