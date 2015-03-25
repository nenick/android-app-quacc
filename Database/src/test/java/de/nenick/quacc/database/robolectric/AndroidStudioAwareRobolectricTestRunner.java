package de.nenick.quacc.database.robolectric;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.manifest.AndroidManifest;
import org.robolectric.res.FileFsFile;
import org.robolectric.res.FsFile;

import de.nenick.quacc.database.BuildConfig;
/**
 * More dynamic path resolution.
 *
 * This workaround is only for Mac Users necessary and only if they don't use the $MODULE_DIR$
 * workaround. Follow this issue at https://code.google.com/p/android/issues/detail?id=158015
 */
public class AndroidStudioAwareRobolectricTestRunner extends RobolectricGradleTestRunner {

    public AndroidStudioAwareRobolectricTestRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    protected AndroidManifest getAppManifest(Config config) {
        AndroidManifest appManifest = super.getAppManifest(config);
        String moduleRoot = getModuleRootPath(config);

        FsFile androidManifestFile = FileFsFile.from(moduleRoot, appManifest.getAndroidManifestFile().getPath());
        if(!androidManifestFile.exists()) {
            androidManifestFile = FileFsFile.from(moduleRoot,  appManifest.getAndroidManifestFile().getPath().replace("bundles", "manifests/full"));
        }
        FsFile resDirectory = FileFsFile.from(moduleRoot, appManifest.getResDirectory().getPath());
        FsFile assetsDirectory = FileFsFile.from(moduleRoot, appManifest.getAssetsDirectory().getPath());
        return new AndroidManifest(androidManifestFile, resDirectory, assetsDirectory);
    }

    private String getModuleRootPath(Config config) {
        String moduleRoot = config.constants().getResource("").toString().replace("file:", "").replace("jar:", "");
        return moduleRoot.substring(0, moduleRoot.indexOf("/build"));
    }
}
