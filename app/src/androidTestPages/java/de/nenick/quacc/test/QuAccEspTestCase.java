package de.nenick.quacc.test;

import android.app.Activity;
import android.app.KeyguardManager;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;

import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspAppDataTool;
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
        Espresso.registerIdlingResources(BackgroundThreadCounter.instance());

        // clear application data
        EspAppDataTool.clearApplicationData();

        // dismiss keyguard
        KeyguardManager keyguardManager = (KeyguardManager) InstrumentationRegistry.getTargetContext().getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
        lock.disableKeyguard();

        activityTestRule.launchActivity(null);
    }
}
