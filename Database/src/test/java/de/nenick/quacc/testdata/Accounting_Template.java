package de.nenick.quacc.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.testdata.base.BaseModel;

public class Accounting_Template extends BaseModel {

    @Column(AccountingTemplateColumns.ACCOUNT_ID)
    long ACCOUNT_ID;

    @Column(AccountingTemplateColumns.COMMENT)
    String COMMENT;

    @Column(AccountingTemplateColumns.INTERVAL)
    String INTERVAL;

    @Column(AccountingTemplateColumns.CATEGORY_ID)
    long CATEGORY_ID;

    @Column(AccountingTemplateColumns.TYPE)
    String TYPE;

    @Column(AccountingTemplateColumns.VALUE)
    int VALUE;

}
