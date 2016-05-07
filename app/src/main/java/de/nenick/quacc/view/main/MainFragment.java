package de.nenick.quacc.view.main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;

import de.nenick.quacc.R;
import de.nenick.quacc.activities.BookingEntriesActivity_;
import de.nenick.quacc.core.initialdata.DatabaseInitialData_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences_;
import de.nenick.quacc.test.BackgroundThreadCounter;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @Pref
    protected QuAccPreferences_ pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

            SQLiteDatabase database = QuAccSQLiteOpenHelper.getInstance(getContext()).getWritableDatabase();
            database.beginTransaction();
            try {
                DatabaseInitialData_.getInstance_(getContext()).insert(database);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
        }

        BookingEntriesActivity_.intent(this).start();
        getActivity().finish();
        BackgroundThreadCounter.decrement();
    }
}
