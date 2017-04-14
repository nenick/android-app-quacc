package de.nenick.quacc.activities.start;

import android.Manifest;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Transactional;

import de.nenick.androidannotations.plugin.mvp.EMvpPresenter;
import de.nenick.quacc.activities.bookingentries.BookingEntriesActivity_;
import de.nenick.quacc.core.initialdata.DatabaseInitialData;
import de.nenick.quacc.database.provider.QuAccSQLiteOpenHelper;
import de.nenick.quacc.settings.QuAccPreferences;
import de.nenick.quacc.test.BackgroundThreadCounter;

@EMvpPresenter
@EActivity
public class StartActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PERMISSION_RECORD_AUDIO = 1;
    private static final String TAG_SPEECH_RECOGNITION_DIALOG = "SPEECH";

    @Bean
    protected QuAccPreferences preferences;

    @Bean
    protected DatabaseInitialData databaseInitialData;

    private SpeechRecognitionDialog speechDialog;

    @AfterInject
    protected void onAfterInjectBeans() {
        storeInitialDatabaseContent();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (preferences.initialAskedForSpeechRecognition()) {
            moveOnToNextActivity();
        } else {
            askUserForSpeechRecognition();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        preferences.activateSpeechRecognition(grantResults[0] == PermissionChecker.PERMISSION_GRANTED);
        moveOnToNextActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speechDialog != null) {
            speechDialog.setCallback(null);
        }
    }

    public void onDeactivateSpeechRecognitionRequested() {
        preferences.initialAskedForSpeechRecognition(true);
        preferences.activateSpeechRecognition(false);
        moveOnToNextActivity();
    }

    public void onActivateSpeechRecognitionRequested() {
        preferences.initialAskedForSpeechRecognition(true);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            preferences.activateSpeechRecognition(true);
            moveOnToNextActivity();
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_PERMISSION_RECORD_AUDIO);
        }
    }

    @Background
    protected void storeInitialDatabaseContent() {
        if(!preferences.initialDatabaseContentStored()) {
            preferences.initialDatabaseContentStored(true);
            storeInitialDatabaseContentTransactional(QuAccSQLiteOpenHelper.getInstance(this).getWritableDatabase());
        }
    }

    @Transactional
    protected void storeInitialDatabaseContentTransactional(SQLiteDatabase database) {
        BackgroundThreadCounter.increment();
        databaseInitialData.insert(database);
        BackgroundThreadCounter.decrement();
    }

    private void moveOnToNextActivity() {
        // must use finishAndRemoveTask instead of noHistory="true" xml flag
        // or onRequestPermissionsResult would never be called
        // and this activity should not pop up when user back press at next activity
        // http://stackoverflow.com/a/35772265/3619179
        finishAndRemoveTask();
        BookingEntriesActivity_.intent(this).start();
    }

    private void askUserForSpeechRecognition() {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        speechDialog = (SpeechRecognitionDialog) supportFragmentManager.findFragmentByTag(TAG_SPEECH_RECOGNITION_DIALOG);
        if (speechDialog == null) {
            speechDialog = new SpeechRecognitionDialog();
            speechDialog.show(supportFragmentManager, TAG_SPEECH_RECOGNITION_DIALOG);
            supportFragmentManager.executePendingTransactions();
        }
        speechDialog.setCallback(this);
    }
}
