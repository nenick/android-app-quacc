package de.nenick.quacc.speechrecognition;

import android.speech.SpeechRecognizer;

public class RoboSpeechRegonitionWrapperHelper {
    public static void setMock(SpeechRecognitionWrapper wrapper, SpeechRecognizer speechRecognizer) {
        wrapper.speechRecognizer = speechRecognizer;
    }
}
