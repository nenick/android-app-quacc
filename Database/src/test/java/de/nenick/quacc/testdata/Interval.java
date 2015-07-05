package de.nenick.quacc.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.testdata.base.BaseModel;

public class Interval extends BaseModel {

    @Column(IntervalColumns.ACCOUNT_ID)
    long ACCOUNT_ID;

    @Column(IntervalColumns.COMMENT)
    String COMMENT;

    @Column(IntervalColumns.INTERVAL)
    long ACCOUNTING_INTERVAL;

    @Column(IntervalColumns.CATEGORY_ID)
    long ACCOUNTING_CATEGORY_ID;

    @Column(IntervalColumns.DATE_END)
    long ACCOUNTING_DATE_END;

    @Column(IntervalColumns.DATE_LAST)
    long ACCOUNTING_DATE_LAST;

    @Column(IntervalColumns.DATE_START)
    long ACCOUNTING_DATE_START;

    @Column(IntervalColumns.DATE_UPDATED_UNTIL)
    long ACCOUNTING_DATE_UPDATED_UNTIL;

    @Column(IntervalColumns.TYPE)
    long ACCOUNTING_TYPE;

    @Column(IntervalColumns.VALUE)
    int VALUE;

}
