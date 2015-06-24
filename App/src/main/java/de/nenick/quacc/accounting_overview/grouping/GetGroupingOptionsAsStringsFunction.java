package de.nenick.quacc.accounting_overview.grouping;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class GetGroupingOptionsAsStringsFunction {

    public Collection<String> apply() {
        GroupingOption[] values = GroupingOption.values();
        List<String> valueStrings = new ArrayList<>();
        for (GroupingOption value : values) {
            valueStrings.add(value.name());
        }
        return valueStrings;
    }
}
