package de.nenick.quacc.accountinglist;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import de.nenick.quacc.accounting.list.GetAccountingListFunction;

@EBean
public class AccountingListPresenter {

    @Bean
    GetAccountingListFunction getAccountingListFunction;

    AccountingListFragment view;

    public void onViewCreated(AccountingListFragment view) {
        this.view = view;
        view.showAccountingList(getAccountingListFunction.apply());
    }
}
