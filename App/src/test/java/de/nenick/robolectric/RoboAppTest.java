package de.nenick.robolectric;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import de.nenick.quacc.BuildConfig;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboAppTest {

    @Before
    public void checkMemory() {
        int mb = 1024*1024;

        //Getting the runtime reference from system
        Runtime runtime = Runtime.getRuntime();

        System.out.println("##### Heap utilization statistics [MB] #####");

        //Print used memory
        System.out.println("Used Memory:"
                + (runtime.totalMemory() - runtime.freeMemory()) / mb);

        //Print free memory
        System.out.println("Free Memory:"
                + runtime.freeMemory() / mb);
    }
}