package de.nenick.quacc.database.provider.testdata;

import org.chalup.microorm.annotations.Column;

import java.util.Date;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.testdata.base.BaseModel;

public class Accounting extends BaseModel {

    @Column(AccountingColumns.ACCOUNT_ID)
    long ACCOUNT_ID;

    @Column(AccountingColumns.NAME)
    String NAME;

    @Column(AccountingColumns.DESCRIPTION)
    String DESCRIPTION;

    @Column(AccountingColumns.ACCOUNTING_INTERVAL_ID)
    long ACCOUNTING_INTERVAL_ID;

    @Column(AccountingColumns.ACCOUNTING_CATEGORY_ID)
    long ACCOUNTING_CATEGORY_ID;

    @Column(AccountingColumns.ACCOUNTING_DATE)
    long ACCOUNTING_DATE;

    @Column(AccountingColumns.ACCOUNTING_TYPE)
    long ACCOUNTING_TYPE;

    @Column(AccountingColumns.VALUE)
    int VALUE;

}
