package de.nenick.quacc.speechrecognition;

import android.os.Bundle;
import android.speech.RecognitionListener;

public abstract class SpeechRecognitionListener implements RecognitionListener {

    @Override
    public void onBeginningOfSpeech() {
        //Log.d(TAG, "onBeginingOfSpeech");
    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {
        //Log.d(TAG, "onEndOfSpeech");
    }

    @Override
    public void onError(int error) {
        //mSpeechRecognizer.startListening(mSpeechRecognizerIntent);

        //Log.d(TAG, "error = " + error);
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
