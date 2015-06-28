package de.nenick.quacc;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

import net.danlew.android.joda.JodaTimeAndroid;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EApplication;
import org.androidannotations.annotations.sharedpreferences.Pref;

import java.io.IOException;
import java.util.logging.LogManager;

import de.nenick.quacc.core.initialdata.DatabaseInitialData_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences_;

@EApplication
public class QuAccApplication extends Application {

    @Pref
    QuAccPreferences_ pref;

    @Override
    public void onCreate() {
        super.onCreate();
        JodaTimeAndroid.init(this);
        try {
            LogManager.getLogManager().readConfiguration(getAssets().open("logging.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterInject
    protected void setupInitialData() {
        if (pref.isFirstAppStart().get()) {
            SQLiteDatabase database = QuAccSQLiteOpenHelper.getInstance(this).getWritableDatabase();
            database.beginTransaction();
            try {
                DatabaseInitialData_.getInstance_(this).insert(database);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
            pref.isFirstAppStart().put(false);
        }
    }
}
