package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accounting.AccountingSelection;

@EBean
public class GetAccountingListUc {

    @RootContext
    Context context;

    public AccountingCursor apply() {
        return new AccountingSelection().query(context.getContentResolver());
    }
}
