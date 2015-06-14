package de.nenick.quacc.view.account;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import de.nenick.quacc.R;
import de.nenick.quacc.view.accounting_overview.AccountingListFragment;

@EActivity(R.layout.activity)
public class AccountsActivity extends ActionBarActivity {

    public static final String TAG_FRAGMENT = AccountingListFragment.class.getSimpleName();

    @AfterViews
    protected void onAfterViews() {
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (fragmentManager.findFragmentByTag(TAG_FRAGMENT) == null) {
            fragmentManager.beginTransaction().add(R.id.container, AccountsFragment_.builder().build(), TAG_FRAGMENT).commit();
        }
    }
}
