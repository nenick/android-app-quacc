package de.nenick.quacc.core.accounting;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import javax.inject.Inject;

import de.nenick.quacc.dagger.DaggerSupport;
import de.nenick.quacc.database.AccountingIntervalRepository;

@EBean
public class GetAccountingIntervalsUc {

    @RootContext
    Context context;

    @Inject
    AccountingIntervalRepository accountingIntervalRepository;

    public CharSequence[] apply() {
        return accountingIntervalRepository.getAccountingIntervals();
    }

    @AfterInject
    public void afterInject() {
        DaggerSupport.inject(this);
    }
}
