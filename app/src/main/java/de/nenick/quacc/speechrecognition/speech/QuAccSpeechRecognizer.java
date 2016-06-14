package de.nenick.quacc.speechrecognition.speech;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.List;
import java.util.Locale;

/**
 * Wrapper for the android speech recognition.
 */
@EBean
public class QuAccSpeechRecognizer {

    @RootContext
    Context context;

    // package public for component test mocking
    SpeechRecognizer speechRecognizer;

    private Intent speechRecognizerIntent;
    private boolean isListening;
    private boolean supported;

    @AfterInject
    protected void afterInject() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = new Intent();
        setSpeechRecognitionIntentValues();

        supported = isSpeechRecognitionAvailable();
    }

    public void setSpeechResultListener(final SpeechResultListener speechResultListener) {
        setRecognitionListener(new RecognizerListenerToSpeechResultListener(speechResultListener));
    }

    public void setRecognitionListener(RecognitionListener recognitionListener) {
        speechRecognizer.setRecognitionListener(
                new RecognizerListenerWithOfflineWorkaround(recognitionListener) {
                    @Override
                    public void onError(int error) {
                        isListening = false;
                        super.onError(error);
                    }

                    @Override
                    public void onResults(Bundle results) {
                        isListening = false;
                        super.onResults(results);
                    }
                });
    }

    public void startListening() {
        if (!isListening && supported) {
            isListening = true;
            speechRecognizer.startListening(speechRecognizerIntent);
        }
    }

    public void stopListening() {
        if (isListening && supported) {
            isListening = false;
            speechRecognizer.cancel();
        }
    }

    public boolean isSpeechRecognitionAvailable() {
        // see also http://stackoverflow.com/questions/4770835/how-to-detect-if-speech-to-text-is-available-on-android
        PackageManager pm = context.getPackageManager();
        List activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        return activities.size() > 0;
    }

    public void destroy() {
        if(supported) {
            speechRecognizer.destroy();
        }
    }

    public boolean isListening() {
        return isListening;
    }

    void setSpeechRecognitionIntentValues() {
        speechRecognizerIntent.setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMANY);
        speechRecognizerIntent.putExtra("android.speech.extra.EXTRA_ADDITIONAL_LANGUAGES", new String[]{});
        //speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_SUPPORTED_LANGUAGES, new String[]{Locale.GERMAN.toString()});
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
    }
}
