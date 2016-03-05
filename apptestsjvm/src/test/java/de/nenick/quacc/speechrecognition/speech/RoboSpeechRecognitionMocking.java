package de.nenick.quacc.speechrecognition.speech;

import android.speech.SpeechRecognizer;

public class RoboSpeechRecognitionMocking {
    public static void setMock(QuAccSpeechRecognizer wrapper, SpeechRecognizer speechRecognizer) {
        wrapper.speechRecognizer = speechRecognizer;
    }
}
