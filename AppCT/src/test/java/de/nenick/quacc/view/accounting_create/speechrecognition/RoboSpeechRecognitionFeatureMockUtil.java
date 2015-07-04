package de.nenick.quacc.view.accounting_create.speechrecognition;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.RoboSpeechRecognitionMocking;

public class RoboSpeechRecognitionFeatureMockUtil {

    public static void setSpeechRecognitionMock(SpeechRecognitionFeature speechRecognitionFeature, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionMocking.setMock(speechRecognitionFeature.quAccSpeechRecognizer, speechRecognizer);
    }
}
