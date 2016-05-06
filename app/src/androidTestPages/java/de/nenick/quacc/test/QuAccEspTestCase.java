package de.nenick.quacc.test;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspAppDataTool;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.R;

public abstract class QuAccEspTestCase<A extends Activity> extends EspressoTestBase<A> {

    @Rule
    public ActivityTestRule<A> activityTestRule = new ActivityTestRule<>(getGenericActivityClass(), false, false);

    @Override
    public A getActivity() {
        return activityTestRule.getActivity();
    }

    public EspButton dummyAppLauncher = EspButton.byId(R.id.btn_start_app);

    @Before
    public void setupQuAcc() {
        QuAccSQLiteOpenHelper.getInstance(InstrumentationRegistry.getTargetContext()).close();
        EspAppDataTool.clearApplicationData();
        activityTestRule.launchActivity(null);
    }
}
