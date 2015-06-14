package de.nenick.quacc.view.speechrecognition;

import android.speech.SpeechRecognizer;

public class RoboSpeechRecognitionMocking {
    public static void setMock(SpeechRecognitionWrapper wrapper, SpeechRecognizer speechRecognizer) {
        wrapper.speechRecognizer = speechRecognizer;
    }
}
