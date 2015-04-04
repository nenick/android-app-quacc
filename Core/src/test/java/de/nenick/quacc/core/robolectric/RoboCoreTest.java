package de.nenick.quacc.core.robolectric;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import de.nenick.quacc.core.BuildConfig;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

@RunWith(AndroidStudioAwareRobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboCoreTest {

    @Spy
    public Context context;

    @Before
    public void prepareRobolectricTest() {
        context = RuntimeEnvironment.application;
    }

    @After
    public void finishRobolectricTest() {
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
