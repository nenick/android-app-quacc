package de.nenick.quacc.addaccounting;

import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.robolectric.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.Collections;

import de.nenick.quacc.R;
import de.nenick.quacc.robolectric.RoboSup;
import de.nenick.quacc.robolectric.RoboSupPage;
import de.nenick.quacc.speechrecognition.RoboSpeechRegonitionWrapperHelper;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboTextView;

import static org.mockito.Mockito.verify;

public class RoboAddAccountingPage extends RoboSupPage<AddAccountingActivity_, AddAccountingFragment> {

    @Captor
    ArgumentCaptor<SpeechListener> speechRecognitionListenerArgumentCaptor;

    public RoboAddAccountingPage(RoboSup<AddAccountingActivity_, AddAccountingFragment> robo) {
        super(robo, AddAccountingActivity.TAG_FRAGMENT);
        MockitoAnnotations.initMocks(this);
    }

    public static Intent Intent() {
        return AddAccountingActivity_.intent(RuntimeEnvironment.application).get();
    }

    public void startPageWithSpeechMock(SpeechRecognizer mockSpeechRecognizer) {
        createPage();
        RoboSpeechRegonitionWrapperHelper.setMock(robo.fragment.speechRecognitionFeature.speechRecognitionWrapper, mockSpeechRecognizer);
        robo.fragment.speechRecognitionFeature.onAfterInject();
        verify(mockSpeechRecognizer).setRecognitionListener(speechRecognitionListenerArgumentCaptor.capture());
        startCreatedPage();
    }

    public RoboSpinner typeSpinner() {
        return new RoboSpinner(robo, R.id.type);
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

    public void speechResult(String... texts) {
        Bundle bundle = new Bundle();
        ArrayList<String> strings = new ArrayList<>();
        Collections.addAll(strings, texts);
        bundle.putStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION, strings);
        speechRecognitionListenerArgumentCaptor.getValue().onResults(bundle);
    }

    public RoboAddAccountingDialogs dialog() {
        return new RoboAddAccountingDialogs();
    }

    public void speechError(int error) {
        speechRecognitionListenerArgumentCaptor.getValue().onError(error);
    }

    public RoboAddAccountingActionbar actionbar() {
        return new RoboAddAccountingActionbar(robo);
    }

    public RoboTextView valueField() {
        return new RoboTextView(robo, R.id.value);
    }

    public RoboTextView commentField() {
        return new RoboTextView(robo, R.id.comment);
    }

    public RoboTextView valueErrorField() {
        return new RoboTextView(robo, R.id.valueError);
    }
}
