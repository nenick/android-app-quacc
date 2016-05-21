package de.nenick.quacc.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.view.accounting_edit.EditAccountingActivity_;

@EActivity
public class BookingEntriesActivity extends AppCompatActivity implements
        BookingEntriesActivityView.ViewCallback,
        LoaderCallback<AccountCursor> {

    @Bean
    protected BookingEntriesActivityView view;

    @Bean
    protected AccountRepository accountRepository;

    @InstanceState
    protected long lastSelectedAccountId;

    private boolean needInitialAccountSelection = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view.root());
    }

    @AfterInject
    protected void onAfterInjectBeans() {
        view.setCallback(this);
    }

    @AfterViews
    protected void onAfterViewsCreated() {
        accountRepository.loader(5463, new AccountSpecAll(), this);
    }

    @Override
    public void onClickNewBookingEntry() {
        EditAccountingActivity_.intent(this).start();
    }

    @Override
    public void onSelectAccount(long accountId) {
        lastSelectedAccountId = accountId;
        view.showAccountContent(lastSelectedAccountId);
    }

    @Override
    public void onLoadFinished(@Nullable AccountCursor cursor) {
        view.showAccounts(cursor);

        if (needInitialAccountSelection) {
            needInitialAccountSelection = false;
            restoreLastOrSelectFirstAccount(cursor);
        }
    }

    private void restoreLastOrSelectFirstAccount(AccountCursor cursor) {
        if (lastSelectedAccountId == 0) {
            cursor.moveToFirst();
            onSelectAccount(cursor.getId());
        } else {
            onSelectAccount(lastSelectedAccountId);
        }
    }
}
