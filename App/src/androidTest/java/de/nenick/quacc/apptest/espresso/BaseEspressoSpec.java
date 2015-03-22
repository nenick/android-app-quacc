package de.nenick.quacc.apptest.espresso;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.jakewharton.test.ActivityRule;

import org.junit.After;
import org.junit.Rule;
import org.junit.runner.RunWith;

import de.nenick.quacc.apptest.DummyLauncherActivity_;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

@RunWith(AndroidJUnit4.class)
public abstract class BaseEspressoSpec {

    @Rule
    public final ActivityRule<DummyLauncherActivity_> main = new ActivityRule<>(DummyLauncherActivity_.class);

    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();

    public void startApp() {
        launcherPage.clickStartApp();
    }

    @After
    public void tearDown() throws Exception {
        CloseAllActivitiesFunction.apply(InstrumentationRegistry.getInstrumentation());
    }
}
