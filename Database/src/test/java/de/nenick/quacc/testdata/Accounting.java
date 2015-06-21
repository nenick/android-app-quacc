package de.nenick.quacc.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.testdata.base.BaseModel;

public class Accounting extends BaseModel {

    @Column(AccountingColumns.ACCOUNT_ID)
    long ACCOUNT_ID;

    @Column(AccountingColumns.COMMENT)
    String COMMENT;

    @Column(AccountingColumns.INTERVAL)
    long ACCOUNTING_INTERVAL;

    @Column(AccountingColumns.CATEGORY_ID)
    long ACCOUNTING_CATEGORY_ID;

    @Column(AccountingColumns.DATE)
    long ACCOUNTING_DATE;

    @Column(AccountingColumns.TYPE)
    long ACCOUNTING_TYPE;

    @Column(AccountingColumns.VALUE)
    int VALUE;

}
