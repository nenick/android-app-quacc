package de.nenick.quacc.accounting.create;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.RoboSpeechRecognitionMocking;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

public class RoboCreateAccountingMocking {

    public static void setSpeechRecognitionMock(CreateAccountingFragment fragment, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionMocking.setMock(fragment.speechRecognitionFeature.speechRecognitionWrapper, speechRecognizer);
        fragment.speechRecognitionFeature.onAfterInject();
    }
}
