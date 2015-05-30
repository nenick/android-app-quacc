package de.nenick.quacc.accounting.list.functions;

import android.content.Context;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.joda.time.DateTime;

import java.util.Date;

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

    public AccountingCursor apply(DateTime startDate, DateTime endDate) {
        startDate = startDate.minusDays(1);
        endDate = endDate.plus(1);
        return accountingDb.getAllBetween(startDate.toDate(), endDate.toDate());
    }
}
