package de.nenick.quacc.speechrecognition;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class SpeechRecognitionWrapper {

    @RootContext
    protected Context context;
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private boolean isListening;

    @AfterInject
    protected void afterInject() {
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(context);
        speechRecognizerIntent = SpeechRecognizerApiIntent.create(context);
    }

    public void toggle() {
        if (!isListening) {
            isListening = true;
            speechRecognizer.startListening(speechRecognizerIntent);
        } else {
            isListening = false;
            speechRecognizer.stopListening();
        }
    }

    public void setRecognitionListener(final SpeechRecognitionListener recognitionListener) {
        speechRecognizer.setRecognitionListener(new SpeechRecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                isListening = false;
                recognitionListener.onResults(results);
            }
        });
    }

    public void destroy() {
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }
}
