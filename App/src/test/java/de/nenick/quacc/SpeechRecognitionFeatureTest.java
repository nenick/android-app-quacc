package de.nenick.quacc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import de.nenick.quacc.speechrecognition.SpeechResultListener;
import de.nenick.quacc.view.accounting_create.CreateAccountingView;
import de.nenick.quacc.view.accounting_create.speechrecognition.InterpretSpeechFunction;
import de.nenick.quacc.view.accounting_create.speechrecognition.InterpretTemplateFunction;
import de.nenick.quacc.view.accounting_create.speechrecognition.SpeechRecognitionFeature;
import de.nenick.quacc.core.speechinterpreter.RecognizeAccountingIntervalFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeAccountingTypeFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeCategoryFunction;
import de.nenick.quacc.core.speechinterpreter.RecognizeValueFunction;
import de.nenick.quacc.speechrecognition.QuAccSpeechRecognizer;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class SpeechRecognitionFeatureTest {

    ArrayList<String> speechResults = new ArrayList<>(1);

    @InjectMocks
    SpeechRecognitionFeature feature;

    @Mock
    CreateAccountingView view;

    @Mock
    QuAccSpeechRecognizer quAccSpeechRecognizer;

    @Mock
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Mock
    RecognizeAccountingIntervalFunction recognizeAccountingIntervalFunction;

    @Mock
    RecognizeCategoryFunction recognizeCategoryFunction;

    @Mock
    RecognizeValueFunction recognizeValueFunction;

    @Mock
    InterpretSpeechFunction interpretSpeechFunction;

    @Mock
    InterpretTemplateFunction interpretTemplateFunction;

    @Captor
    ArgumentCaptor<SpeechResultListener> speechResultListenerArgumentCaptor;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        speechResults.add("my result");
    }

    @Test
    public void shouldReleaseResourcesOnViewFinish() {
        feature.onViewFinish();
        verify(quAccSpeechRecognizer).destroy();
    }

    @Test
    public void shouldStopListeningOnViewPause() {
        feature.onViewPause();
        verify(quAccSpeechRecognizer).stopListening();
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldActivateSpeechRecognitionOnClick() {
        given(quAccSpeechRecognizer.isSpeechRecognitionAvailable()).willReturn(true);
        given(quAccSpeechRecognizer.isListening()).willReturn(false);
        feature.onToggleSpeechRecognitionClick();
        verify(quAccSpeechRecognizer).startListening();
        verify(view).showSpeechStopButton();
    }

    @Test
    public void shouldDeactivateSpeechRecognitionOnClick() {
        given(quAccSpeechRecognizer.isSpeechRecognitionAvailable()).willReturn(true);
        given(quAccSpeechRecognizer.isListening()).willReturn(true);
        feature.onToggleSpeechRecognitionClick();
        verify(quAccSpeechRecognizer).stopListening();
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowHintIfSpeechRecognitionNotAvailable() {
        given(quAccSpeechRecognizer.isSpeechRecognitionAvailable()).willReturn(false);
        feature.onToggleSpeechRecognitionClick();
        verify(view).showToast(R.string.speech_recognition_not_available);
    }

    @Test
    public void shouldShowStartButtonOnError() {
        feature.onAfterInject();
        verify(quAccSpeechRecognizer).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        speechResultListenerArgumentCaptor.getValue().onError(0);
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowStartButtonOnSuccess() {
        feature.onAfterInject();
        verify(quAccSpeechRecognizer).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        speechResultListenerArgumentCaptor.getValue().onResults(speechResults);
        verify(view).showSpeechStartButton();
    }

    @Test
    public void shouldShowRecognizedText() {
        feature.onAfterInject();
        verify(quAccSpeechRecognizer).setSpeechResultListener(speechResultListenerArgumentCaptor.capture());
        ArrayList<String> texts = new ArrayList<>();
        texts.add("Eins");
        speechResultListenerArgumentCaptor.getValue().onResults(texts);
        verify(view).showRecognizedText("[Eins] ");
    }
}
