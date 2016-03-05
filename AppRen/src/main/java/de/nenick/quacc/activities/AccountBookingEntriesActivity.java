package de.nenick.quacc.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.view.accounting_edit.EditAccountingActivity_;
import de.nenick.quacc.view.drawer.AccountNavigationDrawer;
import de.nenick.quacc.view.bookingentries.BookingEntriesFragment;
import de.nenick.quacc.view.drawer.ToolbarDrawerConnection;

@EActivity(R.layout.activity_booking_entries)
public class AccountBookingEntriesActivity extends AppCompatActivity {

    @FragmentById(R.id.fragment_booking_entries)
    BookingEntriesFragment bookingEntries;

    @ViewById(R.id.navigation_drawer)
    AccountNavigationDrawer accountNavigationDrawer;

    @Bean
    ToolbarDrawerConnection toolbarDrawerConnection;

    @Bean
    AccountRepository accountRepository;

    @NonConfigurationInstance
    long lastSelectedAccountId;

    @AfterViews
    void onAfterViewsCreated() {
        selectAccount();
        registerForAccountSelection();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Click(R.id.fab)
    protected void onClickNewBookingEntry() {
        EditAccountingActivity_.intent(this).start();
    }

    private void registerForAccountSelection() {
        accountNavigationDrawer.setAccountSelectionListener(new AccountNavigationDrawer.AccountSelectionListener() {
            @Override
            public void onAccountSelection(long accountId) {
                toolbarDrawerConnection.closeDrawer();
                showAccount(accountId);
            }
        });
    }

    private void showAccount(long accountId) {
        lastSelectedAccountId = accountId;
        bookingEntries.setAccount(accountId);
    }

    private boolean firstAccountResult = true;

    private void selectAccount() {
        accountRepository.loader(5463, new AccountSpecAll(), new LoaderCallback<AccountCursor>() {
            @Override
            public void onLoadFinished(AccountCursor cursor) {
                accountNavigationDrawer.bindAccounts(cursor);

                if(firstAccountResult) {
                    firstAccountResult = false;
                    if(lastSelectedAccountId == 0) {
                        cursor.moveToFirst();
                        lastSelectedAccountId = cursor.getId();
                    }
                    showAccount(lastSelectedAccountId);
                    accountNavigationDrawer.selectAccount(lastSelectedAccountId);
                }

                cursor.close();
            }
        });
    }
}
