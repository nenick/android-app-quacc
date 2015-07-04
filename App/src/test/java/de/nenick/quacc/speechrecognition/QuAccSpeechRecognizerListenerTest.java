package de.nenick.quacc.speechrecognition;

import org.junit.Test;

import static org.mockito.Mockito.mock;

public class QuAccSpeechRecognizerListenerTest {

    QuAccSpeechRecognizerListener listener = new QuAccSpeechRecognizerListener(mock(SpeechResultListener.class));

    @Test
    public void unusedCallbackCoverageCreation() {
        listener.onBufferReceived(null);
        listener.onEndOfSpeech();
        listener.onEvent(0, null);
        listener.onReadyForSpeech(null);
        listener.onRmsChanged(0f);
    }
}