package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.core.R;

@EBean
public class GetAccountsUc {

    @RootContext
    Context context;

    public CharSequence[] apply() {
        return context.getResources().getTextArray(R.array.accounting_categories);
    }
}
