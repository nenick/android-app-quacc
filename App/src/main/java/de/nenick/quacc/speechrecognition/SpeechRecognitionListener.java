package de.nenick.quacc.speechrecognition;

import android.os.Bundle;
import android.speech.RecognitionListener;

/**
 * Hides not necessary callbacks for more clean code usage.
 */
public abstract class SpeechRecognitionListener implements RecognitionListener {

    @Override
    public void onBeginningOfSpeech() {
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
    }

    @Override
    public void onError(int error) {
    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }

    @Override
    public void onPartialResults(Bundle partialResults) {

    }

    @Override
    public void onReadyForSpeech(Bundle params) {

    }

    @Override
    public void onRmsChanged(float rmsdB) {
    }
}
