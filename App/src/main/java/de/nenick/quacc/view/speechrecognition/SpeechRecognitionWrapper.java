package de.nenick.quacc.view.speechrecognition;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@EBean
public class SpeechRecognitionWrapper {

    public interface SpeechResultListener {

        void onError(int error);

        void onResults(ArrayList<String> speechResults);
    }

    @RootContext
    Context context;

    SpeechRecognizer speechRecognizer;
    Intent speechRecognizerIntent;
    boolean isListening;

    @AfterInject
    protected void afterInject() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = createSpeechIntent();
    }

    public void startListening() {
        if (!isListening) {
            isListening = true;
            speechRecognizer.startListening(speechRecognizerIntent);
        }
    }

    public void stopListening() {
        if (isListening) {
            isListening = false;
            speechRecognizer.stopListening();
        }
    }

    public void setSpeechResultListener(final SpeechResultListener speechResultListener) {
        speechRecognizer.setRecognitionListener(new RecognitionListenerWrapper() {
            @Override
            public void onError(int error) {
                isListening = false;
                speechResultListener.onError(error);
            }

            @Override
            public void onResults(Bundle results) {
                isListening = false;
                speechResultListener.onResults(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
            }
        });
    }

    /**
     * http://stackoverflow.com/questions/4770835/how-to-detect-if-speech-to-text-is-available-on-android
     */
    public boolean isSpeechRecognitionAvailable() {
        PackageManager pm = context.getPackageManager();
        List activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        return activities.size() != 0;
    }

    public void destroy() {
        speechRecognizer.destroy();
    }

    public boolean isListening() {
        return isListening;
    }

    private Intent createSpeechIntent() {
        Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Sprackerkennung aktiv");
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
        return mSpeechRecognizerIntent;
    }
}
