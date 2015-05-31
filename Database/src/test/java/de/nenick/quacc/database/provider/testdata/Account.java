package de.nenick.quacc.database.provider.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.testdata.base.BaseModel;

public class Account extends BaseModel {

    @Column(AccountColumns.NAME)
    String NAME;

    @Column(AccountColumns.INITIALVALUE)
    int INITIALVALUE;

}
