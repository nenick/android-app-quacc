package de.nenick.quacc.test;

import android.app.Activity;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Rule;

import java.lang.reflect.Field;

import de.nenick.espressomacchiato.testbase.EspressoTestBase;
import de.nenick.espressomacchiato.tools.EspAppDataTool;
import de.nenick.quacc.core.common.util.QuAccDateUtil;
import de.nenick.quacc.core.common.util.QuAccDateUtil_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences_;
import de.nenick.quacc.test.pages.EspDummyLauncherPage;

public abstract class QuAccEspTestCase<A extends Activity> extends EspressoTestBase<A> {

    @Rule
    public ActivityTestRule<A> activityTestRule = new ActivityTestRule<>(getGenericActivityClass(), false, false);

    @Override
    public A getActivity() {
        return activityTestRule.getActivity();
    }

    public EspDummyLauncherPage launcherPage = new EspDummyLauncherPage();

    public QuAccDateUtil quAccDateUtil;

    @Before
    public void setupQuAcc() {
        Espresso.registerIdlingResources(BackgroundThreadCounter.instance());

        // clear application data
        QuAccSQLiteOpenHelper.getInstance(InstrumentationRegistry.getTargetContext()).close();
        EspAppDataTool.clearApplicationData();
        QuAccPreferences_.getInstance_(InstrumentationRegistry.getTargetContext()).initialAskedForSpeechRecognition(true);

        quAccDateUtil = QuAccDateUtil_.getInstance_(InstrumentationRegistry.getContext());
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
        field.set(quAccDateUtil, new QuAccDateUtil.TodayProvider() {
            @Override
            public DateTime get() {
                // when changing date, keep circleci emulator config in sync with the given day and year
                return DateTime.parse("2016-04-25").withTime(13, 10, 50, 300);
            }
        });
    }
}
