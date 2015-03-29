package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountingCategoryRepository;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryCursor;

@EBean
public class GetAccountingCategoriesUc {

    @Inject
    AccountingCategoryRepository accountingCategoryRepository;

    public CharSequence[] apply() {
        AccountingCategoryCursor accountingCategories = accountingCategoryRepository.getAccountingCategories();
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
