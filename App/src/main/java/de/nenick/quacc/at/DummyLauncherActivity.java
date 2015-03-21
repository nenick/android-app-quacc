package de.nenick.quacc.at;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import de.nenick.quacc.main.MainActivity_;
import de.nenick.quacc.R;

@EActivity(R.layout.activity_dummy_launcher)
public class DummyLauncherActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Click(R.id.btn_start_app)
    protected void onClick() {
        MainActivity_.intent(this).start();
    }
}
