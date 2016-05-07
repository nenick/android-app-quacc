package de.nenick.quacc.view.main;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

import de.nenick.quacc.R;

@EActivity(R.layout.activity)
public class MainActivity extends AppCompatActivity {

    public static final String TAG_FRAGMENT = MainFragment.class.getSimpleName();

    @AfterViews
    protected void onAfterViews() {
        initFragment();
    }

    private void initFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        // If the Fragment is non-null, then it is currently being
        // retained across a configuration change.
        if (fragmentManager.findFragmentByTag(TAG_FRAGMENT) == null) {
            fragmentManager.beginTransaction().add(R.id.container, MainFragment_.builder().build(), TAG_FRAGMENT).commit();
        }
    }
}
