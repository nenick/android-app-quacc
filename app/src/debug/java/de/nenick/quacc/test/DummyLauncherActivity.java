package de.nenick.quacc.test;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import de.nenick.quacc.R;
import de.nenick.quacc.view.main.MainActivity_;

/**
 * Emulates the android launcher for instrumentation tests.
 * <p/>
 * With this dummy we can prepare test data before we start real first activity
 * and write back press specifications without leaving the app.
 * Leaving the app would abort the test and let it fail.
 */
@EActivity(R.layout.activity_dummy_launcher)
public class DummyLauncherActivity extends AppCompatActivity {

    @Click(R.id.btn_start_app)
    protected void onClick() {
        MainActivity_.intent(this).start();
    }
}
