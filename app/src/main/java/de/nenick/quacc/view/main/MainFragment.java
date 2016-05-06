package de.nenick.quacc.view.main;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.sharedpreferences.Pref;

import de.nenick.quacc.R;
import de.nenick.quacc.activities.BookingEntriesActivity_;
import de.nenick.quacc.core.initialdata.DatabaseInitialData_;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences_;

@EFragment(R.layout.fragment_main)
public class MainFragment extends Fragment {

    @Pref
    protected QuAccPreferences_ pref;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BookingEntriesActivity_.intent(this).start();
        getActivity().finish();
    }

    @AfterInject
    protected void setupInitialData() {
        if (pref.isFirstAppStart().get()) {
            SQLiteDatabase database = QuAccSQLiteOpenHelper.getInstance(getContext()).getWritableDatabase();
            database.beginTransaction();
            try {
                DatabaseInitialData_.getInstance_(getContext()).insert(database);
                database.setTransactionSuccessful();
            } finally {
                database.endTransaction();
            }
            pref.isFirstAppStart().put(false);
        }
    }
}
