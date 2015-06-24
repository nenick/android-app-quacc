package de.nenick.quacc.speechrecognition;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

public class RoboSpeechRecognitionMocking {
    public static void setMock(SpeechRecognitionWrapper wrapper, SpeechRecognizer speechRecognizer) {
        wrapper.speechRecognizer = speechRecognizer;
    }
}
