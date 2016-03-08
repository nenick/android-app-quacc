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
import org.robolectric.shadows.ShadowSQLiteConnection;

import java.lang.reflect.Field;
import java.text.NumberFormat;

import de.nenick.quacc.database.BuildConfig;
import de.nenick.quacc.database.QuAccProvider;
import de.nenick.quacc.database.provider.BaseQuAccProvider;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public abstract class RoboDatabaseTest {

    public Context context;

    public RoboDatabaseTest() {
        //ShadowLog.stream = System.out;
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

        // release all database connections to release memory leaks
        // https://github.com/robolectric/robolectric/issues/1510
        ShadowSQLiteConnection.reset();

        // check current allocated memory
        System.gc();
        System.gc();
        System.gc();
        Runtime runtime = Runtime.getRuntime();
        NumberFormat format = NumberFormat.getInstance();
        StringBuilder sb = new StringBuilder();
        long maxMemory = runtime.maxMemory();
        long allocatedMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        sb.append("free memory: " + format.format(freeMemory / 1024) + " ");
        sb.append("allocated memory: " + format.format(allocatedMemory / 1024) + " ");
        sb.append("max memory: " + format.format(maxMemory / 1024) + " ");
        sb.append("total free memory: " + format.format((freeMemory + (maxMemory - allocatedMemory)) / 1024) + " ");
        System.err.println(sb);
        //*/
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
