package de.nenick.quacc.core.bookingentry.direction;

import org.androidannotations.annotations.EBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@EBean
public class GetAccountingTypesFunction {

    public Collection<String> apply() {
        BookingDirectionOption[] values = BookingDirectionOption.values();
        List<String> types = new ArrayList<>();
        for (BookingDirectionOption value : values) {
            if(value == BookingDirectionOption.all) continue;
            types.add(value.name());
        }
        return types;
    }
}
