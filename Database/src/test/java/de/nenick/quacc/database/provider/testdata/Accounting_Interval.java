package de.nenick.quacc.database.provider.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accountinginterval.AccountingIntervalColumns;
import de.nenick.quacc.database.provider.testdata.base.BaseModel;

public class Accounting_Interval extends BaseModel {

    @Column(AccountingIntervalColumns.NAME)
    String NAME;

}
