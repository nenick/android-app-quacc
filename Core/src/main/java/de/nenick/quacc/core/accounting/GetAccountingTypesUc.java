package de.nenick.quacc.core.accounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountingTypeRepository;

@EBean
public class GetAccountingTypesUc {

    @Inject
    AccountingTypeRepository accountingTypeRepository;

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }

    public CharSequence[] apply() {
        return accountingTypeRepository.getAccountingTypes();
    }
}
