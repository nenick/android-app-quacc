package de.nenick.quacc.componenttest;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

import de.nenick.quacc.BuildConfig;

public class AndroidStudioAwareRobolectricTestRunner extends RobolectricTestRunner {

    public AndroidStudioAwareRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        String buildVariant = (BuildConfig.FLAVOR.isEmpty() ? "" : BuildConfig.FLAVOR+ "/") + BuildConfig.BUILD_TYPE;
        String intermediatesPath = BuildConfig.MODULE_PATH + "/build//intermediates";

        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", intermediatesPath + "/manifests/full/" + buildVariant + "/AndroidManifest.xml");
        System.setProperty("android.resources", intermediatesPath + "/res/" + buildVariant);
        System.setProperty("android.assets", intermediatesPath + "/assets/" + buildVariant);
    }
}
