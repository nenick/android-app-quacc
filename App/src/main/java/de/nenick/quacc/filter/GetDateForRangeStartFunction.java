package de.nenick.quacc.filter;

import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.filter.FilterRange;
import de.nenick.quacc.common.util.QuAccDateUtil;

@EBean
public class GetDateForRangeStartFunction {

    public DateTime apply(FilterRange filterRange, int month, String year) {
        DateTime firstDayOfSelectedMonth = QuAccDateUtil.toDateTime(1, month, year);
        if(filterRange == FilterRange.all_accounting) {
            return new DateTime(0); // start from the beginning of time
        } else {
            return firstDayOfSelectedMonth;
        }
    }
}
