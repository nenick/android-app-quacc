package de.nenick.quacc.speechrecognition.hotword;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

/**
 * Hides unused callback methods.
 */
public class RecognizerListenerToHotwordListener implements RecognitionListener {

    HotwordListener wrappedListener;

    public RecognizerListenerToHotwordListener(HotwordListener listener) {
        wrappedListener = listener;
    }

    @Override
    public void onError(int error) {
        wrappedListener.onError(error);
    }

    @Override
    public void onResults(Bundle results) {
        ArrayList<String> result = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        String hotWord = result.get(0);
        wrappedListener.onHotword(hotWord);
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
