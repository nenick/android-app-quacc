package de.nenick.quacc.addaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.Collections;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboTextView;

public class RoboAddAccountingPage extends RoboSupPage<AddAccountingActivity_, AddAccountingFragment> {

    public RoboAddAccountingPage(RoboSup<AddAccountingActivity_, AddAccountingFragment> robo) {
        super(robo, AddAccountingActivity.TAG_FRAGMENT);
    }

    public static Intent Intent() {
        return AddAccountingActivity_.intent(RuntimeEnvironment.application).get();
    }

    public void startPageWithMocks(SpeechRecognitionWrapper mockSpeechRecognition) {
        createPage();
        robo.fragment.speechRecognitionFeature.speechRecognition = mockSpeechRecognition;
        startCreatedPage();
    }

    public RoboSpinner typeSpinner() {
        return new RoboSpinner(robo, R.id.accountingType);
    }

    public RoboTextView dateField() {
        return new RoboTextView(robo, R.id.date);
    }

    public RoboSpinner intervalSpinner() {
        return new RoboSpinner(robo, R.id.interval);
    }

    public RoboSpinner categorySpinner() {
        return new RoboSpinner(robo, R.id.category);
    }

    public RoboSpinner accountSpinner() {
        return new RoboSpinner(robo, R.id.account);
    }

    public RoboTextView speechResultField() {
        return new RoboTextView(robo, R.id.speechResult);
    }

    public RoboImageButton speechButton() {
        return new RoboImageButton(robo, R.id.btn_speech_recognition);
    }

    public void speechResult(String text) {
        speechResult(new String[] {text});
    }

    public void speechResult(String ... texts) {
        Bundle bundle = new Bundle();
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, texts);
        bundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, strings);
        robo.fragment.speechRecognitionFeature.speechRecognition.getRecognitionListener().onResults(bundle);
    }

    public RoboAddAccountingDialogs dialog() {
        return new RoboAddAccountingDialogs();
    }
}
