package de.nenick.quacc.speechrecognition;

import android.os.Bundle;

import org.junit.Test;

public class SpeechRecognitionListenerTest {

    SpeechRecognitionListener listener = new SpeechRecognitionListener() {
        @Override
        public void onError(int error) {
        }

        @Override
        public void onResults(Bundle results) {
        }
    };

    @Test
    public void dummyCallUnusedCallbacks() {
        listener.onBeginningOfSpeech();
        listener.onBufferReceived(null);
        listener.onEndOfSpeech();
        //listener.onError(0);
        listener.onEvent(0, null);
        listener.onPartialResults(null);
        listener.onReadyForSpeech(null);
        //listener.onRmsChanged(0f);
    }
}