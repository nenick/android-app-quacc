# Android Instrumentation Tests

Current they are located inside the real app module because of some issues:

### Issue: manually installation

Test modules requires manually installation of app changes before starting tests.
App module install changes automatically.

https://code.google.com/p/android/issues/detail?id=201081

### Issue: debugging

Debugging tests of test module results in endless "Waiting for application to come online:"
App module does not wait endless for the applications.

https://code.google.com/p/android/issues/detail?id=199374

### Issue: manual run configuration

Just click right and run tests starts the jvm junit runner instead of instrumentation runner.
App module selects correct run configuration automatically.

https://code.google.com/p/android/issues/detail?id=183931

### Issue: not test coverage

# Migrate tests when issues are fixed

Move the *java* folder and the *AndroidManifest.xml* of the *androidTest* folder to *src/main* of this module.
Remove dependencies from androidTestCompile inside app module.