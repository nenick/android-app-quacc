package de.nenick.quacc.accountinglist;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.accounting.GetAccountingListUc;

@EBean
public class AccountingListPresenter {

    @Bean
    GetAccountingListUc getAccountingListUc;

    AccountingListFragment view;

    public void onViewCreated(AccountingListFragment view) {
        this.view = view;
        view.showAccountingList(getAccountingListUc.apply());
    }
}
