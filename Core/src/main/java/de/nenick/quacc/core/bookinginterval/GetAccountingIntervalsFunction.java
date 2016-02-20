package de.nenick.quacc.core.bookinginterval;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class GetAccountingIntervalsFunction {

    public Collection<String> apply() {
        BookingIntervalOption[] values = BookingIntervalOption.values();
        List<String> types = new ArrayList<>();
        for (BookingIntervalOption value : values) {
            if(value == BookingIntervalOption.all) continue;
            types.add(value.name());
        }
        return types;
    }
}
