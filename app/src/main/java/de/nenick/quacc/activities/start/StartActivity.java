package de.nenick.quacc.activities.start;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.sharedpreferences.Pref;

import de.nenick.quacc.activities.bookingentries.BookingEntriesActivity_;
import de.nenick.quacc.core.initialdata.DatabaseInitialData_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences_;
import de.nenick.quacc.test.BackgroundThreadCounter;

@EActivity
public class StartActivity extends AppCompatActivity {

    @Pref
    protected QuAccPreferences_ pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookingEntriesActivity_.intent(this).start();
        finish();
    }

    @AfterInject
    protected void onAfterInject() {
        storeInitialData();
    }

    @Background
    protected void storeInitialData() {
        BackgroundThreadCounter.increment();

        if (pref.isFirstAppStart().get()) {
            pref.isFirstAppStart().put(false);

            SQLiteDatabase database = QuAccSQLiteOpenHelper.getInstance(this).getWritableDatabase();
            database.beginTransaction();
            try {
                DatabaseInitialData_.getInstance_(this).insert(database);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }

        BackgroundThreadCounter.decrement();
    }
}
