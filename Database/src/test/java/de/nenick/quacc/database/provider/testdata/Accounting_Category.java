package de.nenick.quacc.database.provider.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryColumns;
import de.nenick.quacc.database.provider.testdata.base.BaseModel;

public class Accounting_Category extends BaseModel {

    @Column(AccountingCategoryColumns.NAME)
    String NAME;

}
