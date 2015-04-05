package de.nenick.quacc.accountinglist;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.core.accounting.GetAccountingListUc;
import de.nenick.quacc.dagger.DaggerSupport;

@EBean
public class AccountingListPresenter {

    @Inject
    GetAccountingListUc getAccountingListUc;

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }

    AccountingListFragment view;

    public void onViewCreated(AccountingListFragment view) {
        this.view = view;
        view.showAccountingList(getAccountingListUc.apply());
    }
}
