package de.nenick.quacc.accounting.list.functions;

import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.accounting.list.FilterRange;
import de.nenick.quacc.common.util.QuAccDateUtil;

@EBean
public class GetEndDateForRangeFunction {

    public DateTime apply(String filterRange, DateTime firstDayOfSelectedMonth) {
        DateTime endDate;
        switch (FilterRange.valueOf(filterRange)) {
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
            default:
                throw new IllegalStateException(filterRange);
        }
        return endDate;
    }
}
