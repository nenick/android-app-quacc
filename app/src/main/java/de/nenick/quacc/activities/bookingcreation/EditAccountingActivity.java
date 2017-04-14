package de.nenick.quacc.activities.bookingcreation;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;

import de.nenick.androidannotations.plugin.mvp.ActivityLauncher;
import de.nenick.androidannotations.plugin.mvp.EMvpPresenter;
import de.nenick.androidannotations.plugin.mvp.MvpActivity;
import de.nenick.androidannotations.plugin.mvp.MvpFragment;
import de.nenick.quacc.R;
import de.nenick.quacc.view.accounting_edit.EditAccountingFragment;
import de.nenick.quacc.view.accounting_edit.EditAccountingFragment_;

@EMvpPresenter
@EActivity(R.layout.activity)
public class EditAccountingActivity extends AppCompatActivity {

    public static final String TAG_FRAGMENT = EditAccountingFragment.class.getSimpleName();

    @Extra
    long bookingEntryId;

    @MvpFragment
    EditAccountingFragment editAccountingFragment;

    @AfterViews
    protected void onAfterViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (fragmentManager.findFragmentByTag(TAG_FRAGMENT) == null) {
            editAccountingFragment.setBookingEntryId(bookingEntryId);
            fragmentManager.beginTransaction().add(R.id.container, editAccountingFragment, TAG_FRAGMENT).commit();
        }
    }
}
