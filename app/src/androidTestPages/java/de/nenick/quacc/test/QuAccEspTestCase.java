package de.nenick.quacc.test;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;

import java.lang.reflect.Field;

import de.nenick.espressomacchiato.elements.EspButton;
import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspAppDataTool;
import de.nenick.quacc.R;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.common.util.QuAccDateUtil_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

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
        QuAccSQLiteOpenHelper.getInstance(InstrumentationRegistry.getTargetContext()).close();
        EspAppDataTool.clearApplicationData();

        try {
            useFixedDate();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }

        /*/ dismiss keyguard
        KeyguardManager keyguardManager = (KeyguardManager) InstrumentationRegistry.getTargetContext().getSystemService(Activity.KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock lock = keyguardManager.newKeyguardLock(Context.KEYGUARD_SERVICE);
        lock.disableKeyguard();
        */

        activityTestRule.launchActivity(null);
    }

    protected void useFixedDate() throws NoSuchFieldException, IllegalAccessException {
        Field field = QuAccDateUtil.class.getDeclaredField("todayProvider");
        field.setAccessible(true);
        field.set(QuAccDateUtil_.getInstance_(InstrumentationRegistry.getContext()), new QuAccDateUtil.TodayProvider() {
            @Override
            public DateTime get() {
                // when changing date, keep circleci emulator config in sync with the given day and year
                return DateTime.parse("2016-04-25").withTime(13, 10, 50, 300);
            }
        });
    }
}
