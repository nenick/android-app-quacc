package de.nenick.quacc.filter;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.nenick.quacc.filter.FilterRange;

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
