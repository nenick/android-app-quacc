package de.nenick.quacc.speechrecognition.speech;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

/**
 * Hides unused callback methods.
 */
public class RecognizerListenerToSpeechResultListener implements RecognitionListener {

    SpeechResultListener wrappedListener;

    public RecognizerListenerToSpeechResultListener(SpeechResultListener listener) {
        wrappedListener = listener;
    }

    @Override
    public void onError(int error) {
        wrappedListener.onError(error);
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        wrappedListener.onResults(result);
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        // not supported for SpeechResultListener
    }

    @Override
    public void onBeginningOfSpeech() {
        // not supported for SpeechResultListener
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        // not supported for SpeechResultListener
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // not supported for SpeechResultListener
    }

    @Override
    public void onEndOfSpeech() {
        // not supported for SpeechResultListener
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        // not supported for SpeechResultListener
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        // not supported for SpeechResultListener
    }
}
