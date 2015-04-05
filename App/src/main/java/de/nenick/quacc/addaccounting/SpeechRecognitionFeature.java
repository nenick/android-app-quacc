package de.nenick.quacc.addaccounting;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @RootContext
    Context context;

    @ViewById(R.id.btn_speech_recognition)
    FloatingActionButton speechButton;

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    AddAccountingFragment view;
    AddAccountingPresenter presenter;

    public void setHandler(AddAccountingFragment view, AddAccountingPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public void destroy() {
        speechRecognitionWrapper.destroy();
    }

    @AfterInject
    void onAfterInject() {
        speechRecognitionWrapper.setSpeechListener(new SpeechListener() {
            @Override
            public void onError(int error) {
                toggleIsListeningMarker();
            }

            @Override
            public void onResults(Bundle results) {
                presenter.onViewSpeechResult(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
                toggleIsListeningMarker();
            }
        });
    }

    @Click(R.id.btn_speech_recognition)
    protected void onToggleSpeechRecognition() {
        if (isSpeechRecognitionAvailable()) {
            speechRecognitionWrapper.toggle();
        }
        toggleIsListeningMarker();
    }

    private void toggleIsListeningMarker() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if (isListening) {
            speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_micoff));
        } else {
            speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_mic));
        }
    }

    public void onPause() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if (isListening) {
            onToggleSpeechRecognition();
        }
    }

    /**
     * http://stackoverflow.com/questions/4770835/how-to-detect-if-speech-to-text-is-available-on-android
     */
    private boolean isSpeechRecognitionAvailable() {
        PackageManager pm = context.getPackageManager();
        List activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        return activities.size() != 0;
    }
}
