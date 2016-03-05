package de.nenick.robolectric;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

import java.io.File;

import de.nenick.quacc.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;

public class AndroidStudioAwareRobolectricTestRunner extends RobolectricTestRunner {

    public AndroidStudioAwareRobolectricTestRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        String buildVariant = (BuildConfig.FLAVOR.isEmpty() ? "" : BuildConfig.FLAVOR + "/") + BuildConfig.BUILD_TYPE;
        String intermediatesPath = BuildConfig.MODULE_PATH + "/build//intermediates";

        System.setProperty("android.package", BuildConfig.APPLICATION_ID);
        System.setProperty("android.manifest", intermediatesPath + "/manifests/full/" + buildVariant + "/AndroidManifest.xml");
        System.setProperty("android.resources", intermediatesPath + "/res/" + buildVariant);
        System.setProperty("android.assets", intermediatesPath + "/assets/" + buildVariant);
        System.setProperty("java.io.tmpdir", intermediatesPath + "/test-data");
        mkdir(intermediatesPath + "/test-data");
    }

    private void mkdir(String path) {
        File file = new File(path);
        if (!file.exists()) {
            assertThat(file.mkdir()).isTrue();
        }
    }
}
