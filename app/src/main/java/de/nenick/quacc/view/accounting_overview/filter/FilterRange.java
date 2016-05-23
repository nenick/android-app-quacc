package de.nenick.quacc.view.accounting_overview.filter;

public enum  FilterRange {

    /**
     * User will see all booking entries done until now.
     */
    all_accounting,

    /**
     * User see only booking entries from current month.
     * TODO maybe obs
     */
    current_month,

    /**
     * User may select his prefered range
     */
    free
}
