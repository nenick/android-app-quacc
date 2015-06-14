package de.nenick.quacc.view.accounting_create;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.view.speechrecognition.RoboSpeechRecognitionMocking;
import de.nenick.quacc.view.accounting_create.CreateAccountingFragment;

public class RoboCreateAccountingMocking {

    public static void setSpeechRecognitionMock(CreateAccountingFragment fragment, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionMocking.setMock(fragment.speechRecognitionFeature.speechRecognitionWrapper, speechRecognizer);
        fragment.speechRecognitionFeature.onAfterInject();
    }
}
