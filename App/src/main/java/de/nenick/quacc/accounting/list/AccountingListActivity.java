package de.nenick.quacc.accounting.list;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.support.v4.widget.DrawerLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.FragmentById;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.accounts.AccountsActivity_;
import de.nenick.quacc.categories.CategoriesActivity_;

@EActivity(R.layout.activity_accounting_list)
public class AccountingListActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    public static final String TAG_FRAGMENT = AccountingListFragment.class.getSimpleName();

    private CharSequence mTitle;
    private String mAccount;

    @ViewById(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @FragmentById(R.id.navigation_drawer)
    NavigationDrawerFragment navigationDrawerFragment;

    @AfterViews
    protected void onCreateView() {
        mTitle = getTitle();

        // Set up the drawer.
        navigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                drawerLayout);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position == 0) {
            replaceFragment("Girokonto");
        } else if (position == 1) {
            replaceFragment("Bar");
        } else {
            replaceFragment("Tagesgeldkonto");
        }
    }

    private void replaceFragment(String account) {
        mAccount = account;
        FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag(TAG_FRAGMENT)).commit();
        fragmentManager.beginTransaction().replace(R.id.container, AccountingListFragment_.builder().account(account).build(), TAG_FRAGMENT).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!navigationDrawerFragment.isDrawerOpen()) {
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
}
