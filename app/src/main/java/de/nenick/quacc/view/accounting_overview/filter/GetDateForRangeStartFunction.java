package de.nenick.quacc.view.accounting_overview.filter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.core.common.util.QuAccDateUtil;

@EBean
public class GetDateForRangeStartFunction {

    @Bean
    QuAccDateUtil quAccDateUtil;

    public DateTime apply(FilterRange filterRange, int month, String year) {
        DateTime firstDayOfSelectedMonth = quAccDateUtil.toDateTime(1, month, year);
        if(filterRange == FilterRange.all_accounting) {
            return new DateTime(0); // start from the beginning of time
        } else {
            return firstDayOfSelectedMonth;
        }
    }
}
