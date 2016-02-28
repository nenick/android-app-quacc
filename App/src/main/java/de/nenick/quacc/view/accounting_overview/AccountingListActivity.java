package de.nenick.quacc.view.accounting_overview;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.core.backup.BackupFromJsonFileFunction;
import de.nenick.quacc.core.backup.BackupToJsonFileFunction;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.view.account.AccountsActivity_;
import de.nenick.quacc.view.accounting_edit.EditAccountingActivity_;
import de.nenick.quacc.view.bookingentries.BookingEntriesFragment_;
import de.nenick.quacc.view.category.CategoriesActivity_;
import de.nenick.quacc.view.template.TemplateActivity_;

@EActivity(R.layout.activity_accounting_list)
public class AccountingListActivity extends ActionBarActivity
        implements AccountingListDrawer.NavigationDrawerCallbacks {

    public static final String TAG_FRAGMENT = AccountingListFragment.class.getSimpleName();

    private CharSequence mTitle;
    private long mAccount;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @FragmentById(R.id.navigation_drawer)
    AccountingListDrawer accountingListDrawer;

    @Bean
    BackupFromJsonFileFunction backupFromJsonFileFunction;

    @Bean
    BackupToJsonFileFunction backupToJsonFileFunction;

    String backupPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath() + "/QuAcc-Backup.json";

    @AfterViews
    protected void onCreateView() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = getTitle();

        // Set up the drawer.
        accountingListDrawer.setUp(
                R.id.navigation_drawer,
                drawerLayout);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLoaderManager();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        AccountCursor selection = accountingListDrawer.getItem(position);

        String account = getAccountNameByPosition(position);
        if (account.equals(mAccount)) {
            // reload fragment is not necessary if the current and selected account are same
            return;
        }

        mAccount = selection.getId();
        replaceFragmentForCurrentAccount();
    }

    private void replaceFragmentForCurrentAccount() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mAccount == 0) {
            throw new IllegalStateException();
        }
        //fragmentManager.beginTransaction().replace(R.id.container, AccountingListFragment_.builder().account(mAccount).build(), TAG_FRAGMENT).commit();
        fragmentManager.beginTransaction().replace(R.id.container, BookingEntriesFragment_.builder().account(mAccount).build(), TAG_FRAGMENT).commit();
        //fragmentManager.beginTransaction().replace(R.id.container, ExpandListFragment_.builder().build(), TAG_FRAGMENT).commit();
    }

    private String getAccountNameByPosition(int position) {
        String account;
        if (position == 0) {
            account = "Girokonto";
        } else if (position == 1) {
            account = "Bar";
        } else {
            account = "Tagesgeldkonto";
        }
        return account;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!accountingListDrawer.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_accounting_list, menu);
            getSupportActionBar().setTitle(mTitle + " (" + mAccount + ")");
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @OptionsItem(R.id.category)
    protected void onCategoriesEditor() {
        CategoriesActivity_.intent(this).start();
    }

    @OptionsItem(R.id.account)
    protected void onAccountsEditor() {
        AccountsActivity_.intent(this).start();
    }

    @OptionsItem(R.id.template)
    protected void onTemplates() {
        TemplateActivity_.intent(this).start();
    }

    @OptionsItem(R.id.import_data)
    protected void onImport() {
        backupFromJsonFileFunction.apply(backupPath);
        replaceFragmentForCurrentAccount();
    }

    @OptionsItem(R.id.export_data)
    protected void onExport() {
        backupToJsonFileFunction.apply(backupPath);
    }

    @Click(R.id.fab)
    protected void onClickNewBookingEntry() {
        EditAccountingActivity_.intent(this).start();
    }
}
