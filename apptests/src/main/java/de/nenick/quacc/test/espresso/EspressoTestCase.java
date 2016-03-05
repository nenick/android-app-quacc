package de.nenick.quacc.test.espresso;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import de.nenick.quacc.test.DummyLauncherActivity_;
import de.nenick.quacc.test.pages.EspressoDummyLauncherPage;

@RunWith(AndroidJUnit4.class)
public abstract class EspressoTestCase {

    @Rule
    public ActivityTestRule<DummyLauncherActivity_> activityRule = new ActivityTestRule<>(DummyLauncherActivity_.class);

    protected EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();
    protected EspressoDevice device = new EspressoDevice();

    public void startApp() {
        launcherPage.clickStartApp();
    }

    public static class EspressoDevice {
        public void clickBackButton() {
            Espresso.pressBack();
        }
    }

    @Before
    public void setupEspresso() {
        DisableAnimationsFunction.apply(activityRule.getActivity().getApplication());
    }

    /*
    @Before
    public void setupEspresso() {
        //Espresso.registerIdlingResources(AppIdlingResources_.getInstance_(InstrumentationRegistry.getContext()).getIdlingResource());

        clearDatabase();
        avoidLockScreen();

        //DisableAnimationsFunction.apply(activityRule.instrumentation().getContext());
    }

    @After
    public void tearDownEspressoSpec() throws Exception {
        CloseAllActivitiesFunction.apply(activityRule.instrumentation());
    }

    private void clearDatabase() {
        //new ContactSelection().delete(InstrumentationRegistry.getContext().getContentResolver());
        //new AddressSelection().delete(InstrumentationRegistry.getContext().getContentResolver());
    }

    private void avoidLockScreen() {
        // sometimes tests failed on emulator, following approach should avoid it
        // http://stackoverflow.com/questions/22737476/false-positives-junit-framework-assertionfailederror-edittext-is-not-found
        // http://developer.android.com/reference/android/view/WindowManager.LayoutParams.html#FLAG_SHOW_WHEN_LOCKED
        InstrumentationRegistry.getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Activity activity = activityRule.getActivity();
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
    }

    */
}
