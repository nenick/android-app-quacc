package de.nenick.quacc.speechrecognition;

import android.os.Bundle;

import org.junit.Test;

public class RecognitionListenerWrapperTest {

    RecognitionListenerWrapper listener = new RecognitionListenerWrapper() {
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
        listener.onEvent(0, null);
        listener.onPartialResults(null);
        listener.onReadyForSpeech(null);
        listener.onRmsChanged(0f);
    }
}