package de.nenick.quacc.speechrecognition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class SpeechRecognitionWrapper {

    @RootContext
    Context context;
    SpeechRecognizer speechRecognizer;
    Intent speechRecognizerIntent;
    boolean isListening;
    SpeechListener recognitionListener;

    @AfterInject
    protected void afterInject() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = createSpeechIntent();
    }

    public void toggle() {
        if (!isListening) {
            speechRecognizer.startListening(speechRecognizerIntent);
            isListening = true;
        } else {
            isListening = false;
            speechRecognizer.stopListening();
        }
    }

    public void setSpeechListener(final SpeechListener recognitionListener) {
        this.recognitionListener = new SpeechListener() {
            @Override
            public void onError(int error) {
                isListening = false;
                recognitionListener.onError(error);
            }

            @Override
            public void onResults(Bundle results) {
                isListening = false;
                recognitionListener.onResults(results);
            }
        };
        speechRecognizer.setRecognitionListener(this.recognitionListener);
    }

    public void destroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
        recognitionListener = null;
    }

    public boolean isListening() {
        return isListening;
    }

    private Intent createSpeechIntent() {
        Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        return mSpeechRecognizerIntent;
    }

}
