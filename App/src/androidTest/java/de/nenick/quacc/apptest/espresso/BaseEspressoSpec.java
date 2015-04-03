package de.nenick.quacc.apptest.espresso;

import android.content.pm.PackageManager;
import android.os.IBinder;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.view.WindowManager;

import com.jakewharton.test.ActivityRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.runner.RunWith;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import de.nenick.quacc.apptest.DummyLauncherActivity_;
import de.nenick.quacc.apptest.pages.EspressoDummyLauncherPage;

@RunWith(AndroidJUnit4.class)
public abstract class BaseEspressoSpec {

    private static final String TAG = "Primer";
    private static final String ANIMATION_PERMISSION = "android.permission.SET_ANIMATION_SCALE";
    @Rule
    public final ActivityRule<DummyLauncherActivity_> main = new ActivityRule<>(DummyLauncherActivity_.class);
    EspressoDummyLauncherPage launcherPage = new EspressoDummyLauncherPage();

    @Before
    public void setUpEspresso() {
        disableAnimation();
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
    public void tearDown() throws Exception {
        CloseAllActivitiesFunction.apply(main.instrumentation());
    }

    private void disableAnimation() {
        int permStatus = main.instrumentation().getContext().checkCallingOrSelfPermission(ANIMATION_PERMISSION);
        if (permStatus == PackageManager.PERMISSION_GRANTED) {
            if (reflectivelyDisableAnimation()) {
                Log.i(TAG, "All animations disabled.");
            } else {
                Log.i(TAG, "Could not disable animations.");
            }
        } else {
            Log.i(TAG, "Cannot disable animations due to lack of permission.");
        }
    }

    private boolean reflectivelyDisableAnimation() {
        try {
            Class<?> windowManagerStubClazz = Class.forName("android.view.IWindowManager$Stub");
            Method asInterface = windowManagerStubClazz.getDeclaredMethod("asInterface", IBinder.class);
            Class<?> serviceManagerClazz = Class.forName("android.os.ServiceManager");
            Method getService = serviceManagerClazz.getDeclaredMethod("getService", String.class);
            Class<?> windowManagerClazz = Class.forName("android.view.IWindowManager");
            Method setAnimationScales = windowManagerClazz.getDeclaredMethod("setAnimationScales",
                    float[].class);
            Method getAnimationScales = windowManagerClazz.getDeclaredMethod("getAnimationScales");

            IBinder windowManagerBinder = (IBinder) getService.invoke(null, "window");
            Object windowManagerObj = asInterface.invoke(null, windowManagerBinder);
            float[] currentScales = (float[]) getAnimationScales.invoke(windowManagerObj);
            for (int i = 0; i < currentScales.length; i++) {
                currentScales[i] = 0.0f;
            }
            setAnimationScales.invoke(windowManagerObj, currentScales);
            return true;
        } catch (ClassNotFoundException cnfe) {
            Log.w(TAG, "Cannot disable animations reflectively.", cnfe);
        } catch (NoSuchMethodException mnfe) {
            Log.w(TAG, "Cannot disable animations reflectively.", mnfe);
        } catch (SecurityException se) {
            Log.w(TAG, "Cannot disable animations reflectively.", se);
        } catch (InvocationTargetException ite) {
            Log.w(TAG, "Cannot disable animations reflectively.", ite);
        } catch (IllegalAccessException iae) {
            Log.w(TAG, "Cannot disable animations reflectively.", iae);
        } catch (RuntimeException re) {
            Log.w(TAG, "Cannot disable animations reflectively.", re);
        }
        return false;
    }
}
