package de.nenick.quacc.robolectric;

import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import de.nenick.quacc.BuildConfig;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboAppTest {
}
