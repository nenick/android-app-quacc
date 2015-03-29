package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountingRepository;
import de.nenick.quacc.database.provider.accounting.AccountingCursor;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;

@EBean
public class GetAccountingListUc {

    @RootContext
    Context context;

    @Inject
    AccountingRepository accountingRepository;

    public CharSequence[] apply() {
        AccountingCursor accountingCategories = accountingRepository.getAccountings();
        String[] values = new String[accountingCategories.getCount()];
        for (int i = 0; i < accountingCategories.getCount(); i++) {
            accountingCategories.moveToNext();
            values[i] = accountingCategories.getName();
        }
        return values;
    }

    @AfterInject
    public void afterInject() {
        DaggerSupport.inject(this);
    }
}
