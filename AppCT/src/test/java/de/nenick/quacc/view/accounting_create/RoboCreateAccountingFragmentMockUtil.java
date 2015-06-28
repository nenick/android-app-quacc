package de.nenick.quacc.view.accounting_create;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.speechrecognition.RoboSpeechRecognitionMocking;
import de.nenick.quacc.view.accounting_create.speechrecognition.RoboSpeechRecognitionFeatureMockUtil;

public class RoboCreateAccountingFragmentMockUtil {

    public static void setSpeechRecognitionMock(CreateAccountingFragment fragment, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionFeatureMockUtil.setSpeechRecognitionMock(fragment.speechRecognitionFeature, speechRecognizer);
        fragment.speechRecognitionFeature.onAfterInject();
    }
}
