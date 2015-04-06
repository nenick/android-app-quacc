package de.nenick.quacc.robolectric;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import de.nenick.quacc.BuildConfig;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboAppTest {

    @Before
    public void checkMemory() {
        // Total amount of free memory available to the JVM
        System.out.println("Free memory (bytes): " +
                Runtime.getRuntime().freeMemory());
    }
}