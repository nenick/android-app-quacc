package de.nenick.quacc.view.accounting_edit.speechrecognition;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.speech.RoboSpeechRecognitionMocking;

public class RoboSpeechRecognitionFeatureMockUtil {

    public static void setSpeechRecognitionMock(SpeechRecognitionFeature speechRecognitionFeature, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionMocking.setMock(speechRecognitionFeature.quAccSpeechRecognizer, speechRecognizer);
    }
}
