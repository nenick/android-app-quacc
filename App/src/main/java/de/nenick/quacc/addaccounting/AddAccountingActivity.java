package de.nenick.quacc.addaccounting;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;

import de.nenick.quacc.R;

@EActivity(R.layout.activity_add_account_process)
@OptionsMenu(R.menu.menu_add_account_process)
public class AddAccountingActivity extends ActionBarActivity {

    private static final String TAG_FRAGMENT = AddAccountingFragment.class.getSimpleName();

    @AfterViews
    protected void onAfterViews() {
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (fragmentManager.findFragmentByTag(TAG_FRAGMENT) == null) {
            fragmentManager.beginTransaction().add(R.id.container, AddAccountingFragment.build(), TAG_FRAGMENT).commit();
        }
    }

    @OptionsItem(R.id.action_settings)
    void settingsNotImplemented() {

    }
}
