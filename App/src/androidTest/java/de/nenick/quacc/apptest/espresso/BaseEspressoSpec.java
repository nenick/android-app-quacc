package de.nenick.quacc.apptest.espresso;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.view.WindowManager;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

import de.nenick.quacc.apptest.DummyLauncherActivity_;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

@RunWith(AndroidJUnit4.class)
public class BaseEspressoSpec extends ActivityInstrumentationTestCase2<DummyLauncherActivity_> {
    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();

    public BaseEspressoSpec() {
        super(DummyLauncherActivity_.class);
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    public void startApp() {
        getActivity();

        // sometimes tests failed on emulator, following approach should avoid it
        // http://stackoverflow.com/questions/22737476/false-positives-junit-framework-assertionfailederror-edittext-is-not-found
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        });

        launcherPage.clickStartApp();
    }

    @After
    @Override
    public void tearDown() throws Exception {
        CloseAllActivitiesFunction.apply(getInstrumentation());
        super.tearDown();
    }
}
