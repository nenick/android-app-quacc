package de.nenick.quacc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import de.nenick.quacc.view.accounting_create.CreateAccountingView;
import de.nenick.quacc.view.accounting_create.speechrecognition.SpeechRecognitionFeature;
import de.nenick.quacc.speechrecognition.RecognizeAccountingIntervalFunction;
import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;
import de.nenick.quacc.speechrecognition.RecognizeCategoryFunction;
import de.nenick.quacc.speechrecognition.RecognizeValueFunction;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SpeechRecognitionFeatureTest {

    ArrayList<String> speechResults = new ArrayList<>(1);

    @InjectMocks
    SpeechRecognitionFeature feature;

    @Mock
    CreateAccountingView view;

    @Mock
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Mock
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Mock
    RecognizeAccountingIntervalFunction recognizeAccountingIntervalFunction;

    @Mock
    RecognizeCategoryFunction recognizeCategoryFunction;

    @Mock
    RecognizeValueFunction recognizeValueFunction;

    @Captor
    ArgumentCaptor<SpeechRecognitionWrapper.SpeechResultListener> speechResultListenerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        speechResults.add("my result");
    }

    @Test
    public void shouldReleaseResourcesOnViewFinish() {
        feature.onViewFinish();
        verify(speechRecognitionWrapper).destroy();
    }

    @Test
    public void shouldStopListeningOnViewPause() {
        feature.onViewPause();
        verify(speechRecognitionWrapper).stopListening();
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldActivateSpeechRecognitionOnClick() {
        given(speechRecognitionWrapper.isSpeechRecognitionAvailable()).willReturn(true);
        given(speechRecognitionWrapper.isListening()).willReturn(false);
        feature.onToggleSpeechRecognitionClick();
        verify(speechRecognitionWrapper).startListening();
        verify(view).showSpeechStopButton();
    }

    @Test
    public void shouldDeactivateSpeechRecognitionOnClick() {
        given(speechRecognitionWrapper.isSpeechRecognitionAvailable()).willReturn(true);
        given(speechRecognitionWrapper.isListening()).willReturn(true);
        feature.onToggleSpeechRecognitionClick();
        verify(speechRecognitionWrapper).stopListening();
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowHintIfSpeechRecognitionNotAvailable() {
        given(speechRecognitionWrapper.isSpeechRecognitionAvailable()).willReturn(false);
        feature.onToggleSpeechRecognitionClick();
        verify(view).showToast(R.string.speech_recognition_not_available);
    }

    @Test
    public void shouldShowStartButtonOnError() {
        feature.onAfterInject();
        verify(speechRecognitionWrapper).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        speechResultListenerArgumentCaptor.getValue().onError(0);
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowStartButtonOnSuccess() {
        feature.onAfterInject();
        verify(speechRecognitionWrapper).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        speechResultListenerArgumentCaptor.getValue().onResults(speechResults);
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowRecognizedText() {
        feature.onAfterInject();
        verify(speechRecognitionWrapper).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        ArrayList<String> texts = new ArrayList<>();
        texts.add("Eins");
        speechResultListenerArgumentCaptor.getValue().onResults(texts);
        verify(view).showRecognizedText("[Eins]");
    }
}
