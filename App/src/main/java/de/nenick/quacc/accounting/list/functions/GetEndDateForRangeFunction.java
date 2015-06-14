package de.nenick.quacc.accounting.list.functions;

import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.accounting.list.FilterRange;
import de.nenick.quacc.common.util.QuAccDateUtil;

@EBean
public class GetEndDateForRangeFunction {

    public DateTime apply(FilterRange filterRange, DateTime firstDayOfSelectedMonth) {
        DateTime endDate;
        switch (filterRange) {
            case all_accounting:
                DateTime currentDate = QuAccDateUtil.currentDateTime();
                if(firstDayOfSelectedMonth.monthOfYear().equals(currentDate.monthOfYear())) {
                    endDate = currentDate;
                } else {
                    endDate = QuAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
                }
                break;
            case current_month:
                endDate = QuAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
                break;
            case free:
                endDate = QuAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
                break;
            default:
                throw new IllegalStateException(filterRange.name());
        }
        return endDate;
    }
}
