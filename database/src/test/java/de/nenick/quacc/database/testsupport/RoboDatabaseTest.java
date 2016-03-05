package de.nenick.quacc.database.testsupport;

import android.content.Context;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowLog;

import java.lang.reflect.Field;

import de.nenick.quacc.database.BuildConfig;
import de.nenick.quacc.database.QuAccProvider;
import de.nenick.quacc.database.provider.BaseQuAccProvider;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboDatabaseTest {

    public Context context;

    public RoboDatabaseTest() {
        ShadowLog.stream = System.out;
        ShadowLog.setLoggable(BaseQuAccProvider.class.getSimpleName(), Log.DEBUG);
        ShadowLog.setLoggable("CursorWindowStats", Log.WARN);
        ShadowLog.setLoggable(SQLiteCursor.class.getSimpleName(), Log.WARN);
    }

    @Before
    public void prepareRobolectricTest() {
        context = RuntimeEnvironment.application;
        initialiseDatabase();
    }

    private void initialiseDatabase() {
        long startTime = System.currentTimeMillis();
        SQLiteDatabase db = QuAccSQLiteOpenHelper.getInstance(context).getWritableDatabase();
        db.beginTransaction();
        db.endTransaction();
        Log.d(QuAccProvider.TAG, "initialise database elapsed time=" + (System.currentTimeMillis() - startTime));
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
