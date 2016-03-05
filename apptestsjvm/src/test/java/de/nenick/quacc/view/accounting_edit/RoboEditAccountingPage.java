package de.nenick.quacc.view.accounting_edit;

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
import de.nenick.quacc.speechrecognition.speech.RecognizerListenerWithOfflineWorkaround;
import de.nenick.robolectric.RoboSup;
import de.nenick.robolectric.RoboSupPage;
import de.nenick.robolectricpages.components.RoboImageButton;
import de.nenick.robolectricpages.components.RoboSpinner;
import de.nenick.robolectricpages.components.RoboTextView;

import static org.mockito.Mockito.verify;

public class RoboEditAccountingPage extends RoboSupPage<EditAccountingActivity_, EditAccountingFragment> {

    @Captor
    ArgumentCaptor<RecognizerListenerWithOfflineWorkaround> speechRecognitionListenerArgumentCaptor;

    public RoboEditAccountingPage() {
        super(new RoboSup<EditAccountingActivity_, EditAccountingFragment>(), EditAccountingActivity.TAG_FRAGMENT);
        MockitoAnnotations.initMocks(this);
    }

    public static Intent Intent() {
        return EditAccountingActivity_.intent(RuntimeEnvironment.application).get();
    }

    public void startPageWithSpeechMock(SpeechRecognizer mockSpeechRecognizer) {
        createPage();
        RoboEditAccountingFragmentMockUtil.setSpeechRecognitionMock(robo.fragment, mockSpeechRecognizer);
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

    public RoboCategorySpinner categorySpinner() {
        return new RoboCategorySpinner(robo, R.id.category);
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

    public RoboEditAccountingDialogs dialog() {
        return new RoboEditAccountingDialogs();
    }

    public void speechError(int error) {
        speechRecognitionListenerArgumentCaptor.getValue().onError(error);
    }

    public RoboEditAccountingActionbar actionbar() {
        return new RoboEditAccountingActionbar(robo);
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

    public RoboTextView endDateField() {
        return new RoboTextView(robo, R.id.endDate);
    }
}
