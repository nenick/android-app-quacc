package de.nenick.quacc.activities.bookingentries;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;

import de.nenick.quacc.activities.bookingcreation.EditAccountingActivity_;
import de.nenick.quacc.database.LoaderCallback;
import de.nenick.quacc.database.account.AccountRepository;
import de.nenick.quacc.database.account.AccountSpecAll;
import de.nenick.quacc.database.provider.account.AccountCursor;
import de.nenick.quacc.speechrecognition.hotword.HotwordListener;
import de.nenick.quacc.speechrecognition.hotword.QuAccHotwordRecognizer;

@EActivity
public class BookingEntriesActivity extends AppCompatActivity implements
        BookingEntriesView.ViewCallback,
        LoaderCallback<AccountCursor> {

    @Bean
    protected BookingEntriesView view;

    @Bean
    protected AccountRepository accountRepository;

    @Bean
    protected QuAccHotwordRecognizer quAccHotwordRecognizer;

    @InstanceState
    protected long lastSelectedAccountId;

    private boolean needInitialAccountSelection = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(view.root());
    }

    @AfterInject
    protected void onAfterInjectBeans() {
        view.setCallback(this);
        quAccHotwordRecognizer.setHotwordListener(new HotwordListener() {
            @Override
            public void onError(int error) {
                onSpeechRecognitionError(error);
            }

            @Override
            public void onHotword(String hotword) {
                onClickNewBookingEntry();
            }

        }, "Neuer Eintrag", "Neu");
    }

    @AfterViews
    protected void onAfterViewsCreated() {
        accountRepository.loader(5463, new AccountSpecAll(), this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PermissionChecker.PERMISSION_GRANTED) {
            quAccHotwordRecognizer.startListening();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        quAccHotwordRecognizer.stopListening();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        quAccHotwordRecognizer.destroy();
    }

    @Override
    public void onClickNewBookingEntry() {
        EditAccountingActivity_.intent(this).start();
    }

    @Override
    public void onSelectAccount(long accountId) {
        lastSelectedAccountId = accountId;
        view.showAccountContent(lastSelectedAccountId);
    }

    @Override
    public void onLoadFinished(@Nullable AccountCursor cursor) {
        view.showAccounts(cursor);

        if (needInitialAccountSelection) {
            needInitialAccountSelection = false;
            restoreLastOrSelectFirstAccount(cursor);
        }
    }

    private void restoreLastOrSelectFirstAccount(AccountCursor cursor) {
        if (lastSelectedAccountId == 0) {
            cursor.moveToFirst();
            onSelectAccount(cursor.getId());
        } else {
            onSelectAccount(lastSelectedAccountId);
        }
    }

    private void onSpeechRecognitionError(int error) {
        Toast.makeText(this, "speech recognition error: " + error, Toast.LENGTH_SHORT).show();
    }
}
