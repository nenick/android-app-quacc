package de.nenick.quacc.accounting.list;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.AccountingDb;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;

@EBean
public class GetAccountingListFunction {

    @RootContext
    Context context;

    @Bean
    AccountingDb accountingDb;

    public AccountingCursor apply() {
        return accountingDb.getAll();
    }
}
