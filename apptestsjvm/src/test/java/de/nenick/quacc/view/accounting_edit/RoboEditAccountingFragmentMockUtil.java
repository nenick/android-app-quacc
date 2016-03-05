package de.nenick.quacc.view.accounting_edit;

import android.speech.SpeechRecognizer;

import de.nenick.quacc.view.accounting_edit.speechrecognition.RoboSpeechRecognitionFeatureMockUtil;

public class RoboEditAccountingFragmentMockUtil {

    public static void setSpeechRecognitionMock(EditAccountingFragment fragment, SpeechRecognizer speechRecognizer) {
        RoboSpeechRecognitionFeatureMockUtil.setSpeechRecognitionMock(fragment.speechRecognitionFeature, speechRecognizer);
        fragment.speechRecognitionFeature.onAfterInject();
    }
}
