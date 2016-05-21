package de.nenick.quacc.activities.start;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Transactional;

import de.nenick.quacc.activities.bookingentries.BookingEntriesActivity_;
import de.nenick.quacc.core.initialdata.DatabaseInitialData;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences;
import de.nenick.quacc.test.BackgroundThreadCounter;

@EActivity
public class StartActivity extends AppCompatActivity {

    @Bean
    protected QuAccPreferences preferences;

    @Bean
    protected DatabaseInitialData databaseInitialData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookingEntriesActivity_.intent(this).start();
        finish();
    }

    @AfterInject
    protected void onAfterInject() {
        SQLiteDatabase database = QuAccSQLiteOpenHelper.getInstance(this).getWritableDatabase();
        storeInitialData(database);
    }

    @Background
    @Transactional
    protected void storeInitialData(SQLiteDatabase database) {
        BackgroundThreadCounter.increment();
        if (preferences.isFirstAppStart()) {
            preferences.isFirstAppStart(false);
            databaseInitialData.insert(database);
        }
        BackgroundThreadCounter.decrement();
    }
}
