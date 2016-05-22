package de.nenick.quacc.view.accounting_overview.filter;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;

import de.nenick.quacc.core.common.util.QuAccDateUtil;

@EBean
public class GetDateForRangeEndFunction {

    @Bean
    QuAccDateUtil quAccDateUtil;

    public DateTime apply(FilterRange filterRange, DateTime firstDayOfSelectedMonth) {
        switch (filterRange) {
            case all_accounting:
                return QuAccDateUtil.currentDateTime();
            case current_month:
                return quAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
            case free:
                return quAccDateUtil.lastDayOfMonth(firstDayOfSelectedMonth);
            default:
                throw new IllegalStateException(filterRange.name());
        }
    }
}
