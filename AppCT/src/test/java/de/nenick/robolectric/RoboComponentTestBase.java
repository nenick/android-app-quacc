package de.nenick.robolectric;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RuntimeEnvironment;

import java.lang.reflect.Field;

import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
public abstract class RoboComponentTestBase {

    public Context context;

    @Before
    public void setUpComponentTesting() {
        context = RuntimeEnvironment.application;
    }

    @After
    public void finishComponentTesting() {
        resetSingleton(QuAccSQLiteOpenHelper.class, "sInstance");
    }

    private void resetSingleton(Class clazz, String fieldName) {
        Field instance;
        try {
            instance = clazz.getDeclaredField(fieldName);
            instance.setAccessible(true);
            instance.set(null, null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
