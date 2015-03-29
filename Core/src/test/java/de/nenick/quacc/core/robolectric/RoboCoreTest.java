package de.nenick.quacc.core.robolectric;

import android.content.Context;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import de.nenick.quacc.core.BuildConfig;
import de.nenick.quacc.core.TestApplication;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
@Config(constants = BuildConfig.class, application = TestApplication.class)
public abstract class RoboCoreTest {

    @Spy
    public Context context;

    @Before
    public void prepareRobolectricTest() {
        context = RuntimeEnvironment.application;
    }

}
