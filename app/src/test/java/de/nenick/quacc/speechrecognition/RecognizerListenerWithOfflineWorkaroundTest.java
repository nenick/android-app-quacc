package de.nenick.quacc.speechrecognition;

import android.speech.RecognitionListener;

import org.junit.Test;

import de.nenick.quacc.speechrecognition.speech.RecognizerListenerWithOfflineWorkaround;
import de.nenick.quacc.speechrecognition.speech.SpeechResultListener;

import static org.mockito.Mockito.mock;

public class RecognizerListenerWithOfflineWorkaroundTest {

    RecognizerListenerWithOfflineWorkaround listener = new RecognizerListenerWithOfflineWorkaround(mock(RecognitionListener.class));

    @Test
    public void unusedCallbackCoverageCreation() {
        listener.onBufferReceived(null);
        listener.onEndOfSpeech();
        listener.onEvent(0, null);
        listener.onReadyForSpeech(null);
        listener.onRmsChanged(0f);
    }
}