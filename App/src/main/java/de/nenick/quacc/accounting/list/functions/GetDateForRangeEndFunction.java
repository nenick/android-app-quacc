package de.nenick.quacc.accounting.list.functions;

import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.accounting.list.FilterRange;
import de.nenick.quacc.common.util.QuAccDateUtil;

@EBean
public class GetDateForRangeEndFunction {

    public DateTime apply(FilterRange filterRange, DateTime firstDayOfSelectedMonth) {
        switch (filterRange) {
            case all_accounting:
                return QuAccDateUtil.currentDateTime();
            case current_month:
                return QuAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
            case free:
                return QuAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
            default:
                throw new IllegalStateException(filterRange.name());
        }
    }
}
