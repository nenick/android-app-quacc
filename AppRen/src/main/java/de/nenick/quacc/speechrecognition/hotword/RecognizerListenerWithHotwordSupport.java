package de.nenick.quacc.speechrecognition.hotword;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

/**
 * Wrapper self restarting speech recognition support.
 */
public class RecognizerListenerWithHotwordSupport implements RecognitionListener {

    private RecognitionListener wrappedRecognitionListener;

    public RecognizerListenerWithHotwordSupport(RecognitionListener recognitionListener) {
        wrappedRecognitionListener = recognitionListener;
    }

    @Override
    public void onBeginningOfSpeech() {
        wrappedRecognitionListener.onBeginningOfSpeech();
    }

    @Override
    public void onResults(Bundle results) {
        wrappedRecognitionListener.onResults(results);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        wrappedRecognitionListener.onPartialResults(partialResults);
    }

    @Override
    public void onError(int error) {
        wrappedRecognitionListener.onError(error);
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        wrappedRecognitionListener.onBufferReceived(buffer);
    }

    @Override
    public void onEndOfSpeech() {
        wrappedRecognitionListener.onEndOfSpeech();
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        wrappedRecognitionListener.onEvent(eventType, params);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        wrappedRecognitionListener.onReadyForSpeech(params);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        wrappedRecognitionListener.onRmsChanged(rmsdB);
    }
}
