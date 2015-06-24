package de.nenick.quacc.accounting_create;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.RoboSpeechRecognitionMocking;

public class RoboCreateAccountingMocking {

    public static void setSpeechRecognitionMock(CreateAccountingFragment fragment, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionMocking.setMock(fragment.speechRecognitionFeature.speechRecognitionWrapper, speechRecognizer);
        fragment.speechRecognitionFeature.onAfterInject();
    }
}
