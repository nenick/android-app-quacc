package de.nenick.quacc.apptest.espresso;

import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import com.jakewharton.test.ActivityRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import de.nenick.quacc.apptest.DummyLauncherActivity_;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

@RunWith(AndroidJUnit4.class)
public abstract class BaseEspressoSpec {

    @Rule
    public final ActivityRule<DummyLauncherActivity_> main = new ActivityRule<>(DummyLauncherActivity_.class);
    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();

    @Before
    public void setUpEspressoSpec() {
        DisableAnimationsFunction.apply(main.instrumentation().getContext());
    }

    public void startApp() {
        main.instrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                main.get().getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
            }
        });

        launcherPage.clickStartApp();
    }

    @After
    public void tearDownEspressoSpec() throws Exception {
        CloseAllActivitiesFunction.apply(main.instrumentation());
    }


}
