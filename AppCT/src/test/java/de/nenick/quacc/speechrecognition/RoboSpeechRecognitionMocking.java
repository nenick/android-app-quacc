package de.nenick.quacc.speechrecognition;

import android.speech.SpeechRecognizer;

public class RoboSpeechRecognitionMocking {
    public static void setMock(QuAccSpeechRecognizer wrapper, SpeechRecognizer speechRecognizer) {
        wrapper.speechRecognizer = speechRecognizer;
    }
}
