package de.nenick.quacc.accounting.create;

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

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @RootContext
    Context context;

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Bean
    RecognizeAccountingTypeUc recognizeAccountingTypeUc;

    CreateAccountingView view;

    public void setView(CreateAccountingView view) {
        this.view = view;
    }

    public void destroy() {
        speechRecognitionWrapper.destroy();
    }

    public void stop() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if (isListening) {
            onToggleSpeechRecognition();
        }
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
                onViewSpeechResult(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
                toggleIsListeningMarker();
            }
        });
    }

    @Click(R.id.btn_speech_recognition)
    void onToggleSpeechRecognition() {
        if (isSpeechRecognitionAvailable()) {
            speechRecognitionWrapper.toggle();
        }
        toggleIsListeningMarker();
    }

    private void toggleIsListeningMarker() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if (isListening) {
            view.showSpeechStopButton();
        } else {
            view.showSpeechStartButton();
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

    private void onViewSpeechResult(ArrayList<String> matches) {
        if (matches == null || matches.size() < 1) {
            throw new UnsupportedOperationException("Getting no match was never tested");
        }

        String accountingType = recognizeAccountingTypeUc.apply(matches);

        String recognizedText = "";
        for (String match : matches) {
            recognizedText += "[" + match + "] ";
        }
        view.showRecognizedText(recognizedText);
    }
}
