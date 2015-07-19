package de.nenick.quacc.speechrecognition.speech;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import de.nenick.quacc.speechrecognition.speech.QuAccSpeechRecognizer;
import de.nenick.quacc.speechrecognition.speech.RecognizerListenerWithOfflineWorkaround;
import de.nenick.quacc.speechrecognition.speech.SpeechResultListener;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class QuAccSpeechRecognizerTest {

    @Mock
    Context context;

    @Mock
    Intent speechRecognizerIntent;

    @Mock
    SpeechRecognizer _speechRecognizer;

    @Mock
    SpeechResultListener speechResultListener;

    @InjectMocks
    QuAccSpeechRecognizer quAccSpeechRecognizer;

    @Captor
    ArgumentCaptor<RecognizerListenerWithOfflineWorkaround> recognitionListenerWrapperArgumentCaptor;

    RecognizerListenerWithOfflineWorkaround recognizerListenerWithOfflineWorkaround;

    @Mock
    Bundle resultBundle;

    @Mock
    Bundle partialResultBundle;

    ArrayList<String> partialSpeechRecognizerLastPart;
    ArrayList<String> partialSpeechRecognizerResult;
    ArrayList<String> speechRecognitionResult;
    String packageName = "de.nenick";

    List<ResolveInfo> activitiesForSpeechRecognition = new ArrayList<>();

    @Mock
    PackageManager packageManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        createResultBundle();
        createPartialResultBundle();
        initialiseListener();
        given(context.getPackageName()).willReturn(packageName);
        given(context.getPackageManager()).willReturn(packageManager);
        given(packageManager.queryIntentActivities(any(Intent.class), anyInt())).willReturn(activitiesForSpeechRecognition);
    }

    @Test
    public void shouldStartWithoutListening() throws Exception {
        thenSpeechRecognitionIsNotListening();
    }

    @Test
    public void shouldStartListening() throws Exception {
        whenStartListening();
        thenSpeechRecognitionIsListening();
        verify(_speechRecognizer).startListening(any(Intent.class));
    }

    @Test
    public void shouldStartListeningOnce() throws Exception {
        whenStartListening();
        thenSpeechRecognitionIsListening();
        thenSpeechRecognitionIsListening();
        verify(_speechRecognizer).startListening(any(Intent.class));
    }

    @Test
    public void shouldStopListening() throws Exception {
        whenStartListening();
        thenSpeechRecognitionIsListening();
        whenStopListening();
        thenSpeechRecognitionIsNotListening();
        verify(_speechRecognizer).stopListening();
    }

    @Test
    public void shouldStopListeningOnce() throws Exception {
        whenStartListening();
        thenSpeechRecognitionIsListening();
        whenStopListening();
        thenSpeechRecognitionIsNotListening();
        thenSpeechRecognitionIsNotListening();
        verify(_speechRecognizer).stopListening();
    }

    @Test
    public void shouldIgnorePartialResult() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onResults(resultBundle);
        verify(speechResultListener).onResults(speechRecognitionResult);
    }

    @Test
    public void shouldReportPartialResult() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onResults(partialSpeechRecognizerResult);
    }

    @Test
    public void shouldReportErrorNoMatch() {
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onError(SpeechRecognizer.ERROR_NO_MATCH);
    }

    @Test
    public void shouldResetPartialResults() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onBeginningOfSpeech();
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onError(SpeechRecognizer.ERROR_NO_MATCH);
    }

    @Test
    public void shouldReportOnlyOneOnce() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(resultBundle);
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onResults(partialSpeechRecognizerResult);

        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verifyNoMoreInteractions(speechResultListener);
    }

    @Test
    public void shouldReportAfterRestart() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onResults(partialSpeechRecognizerResult);
        reset(speechResultListener);

        recognizerListenerWithOfflineWorkaround.onBeginningOfSpeech();

        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onResults(partialSpeechRecognizerResult);
    }

    @Test
    public void shouldReportLastPartialResults() {
        recognizerListenerWithOfflineWorkaround.onPartialResults(resultBundle);
        recognizerListenerWithOfflineWorkaround.onPartialResults(partialResultBundle);
        recognizerListenerWithOfflineWorkaround.onError(SpeechRecognizer.ERROR_NO_MATCH);
        verify(speechResultListener).onResults(partialSpeechRecognizerResult);
    }

    @Test
    public void shouldSetCorrectSpeechRecognitionIntentValues() {
        quAccSpeechRecognizer.setSpeechRecognitionIntentValues();
        thenRequestSpeechRecognition();
        thenUseFreeFormForMatching();
        thenSetPackageNameFromApp();
        thenUseGermanAsDefaultLanguage();
        thenEnablePartialResultsForOfflineRecognition();
    }

    @Test
    public void shouldDestroySpeechRecognizer() {
        quAccSpeechRecognizer.destroy();
        verify(_speechRecognizer).destroy();
    }

    @Test
    public void shouldReportSpeechRecognitionAvailable() {
        activitiesForSpeechRecognition.add(mock(ResolveInfo.class));
        assertThat(quAccSpeechRecognizer.isSpeechRecognitionAvailable()).isTrue();
    }

    @Test
    public void shouldReportSpeechRecognitionNotAvailable() {
        assertThat(activitiesForSpeechRecognition).isEmpty();
        assertThat(quAccSpeechRecognizer.isSpeechRecognitionAvailable()).isFalse();
    }

    private void createPartialResultBundle() {
        partialSpeechRecognizerResult = new ArrayList<>();
        partialSpeechRecognizerResult.add("Some partial");
        partialSpeechRecognizerLastPart = new ArrayList<>();
        partialSpeechRecognizerLastPart.add("text");
        given(partialResultBundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)).willReturn(partialSpeechRecognizerResult);
        given(partialResultBundle.getStringArrayList("android.speech.extra.UNSTABLE_TEXT")).willReturn(partialSpeechRecognizerLastPart);
    }

    private void createResultBundle() {
        speechRecognitionResult = new ArrayList<>();
        speechRecognitionResult.add("Some result text");
        given(resultBundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)).willReturn(speechRecognitionResult);
    }

    private void thenSpeechRecognitionIsNotListening() {
        assertThat(quAccSpeechRecognizer.isListening()).isFalse();
    }

    private void thenSpeechRecognitionIsListening() {
        assertThat(quAccSpeechRecognizer.isListening()).isTrue();
    }

    private void whenStartListening() {
        quAccSpeechRecognizer.startListening();
    }

    private void whenStopListening() {
        quAccSpeechRecognizer.stopListening();
    }

    private void initialiseListener() {
        quAccSpeechRecognizer.setSpeechResultListener(speechResultListener);
        verify(_speechRecognizer).setRecognitionListener(recognitionListenerWrapperArgumentCaptor.capture());
        recognizerListenerWithOfflineWorkaround = recognitionListenerWrapperArgumentCaptor.getValue();
    }

    private void thenEnablePartialResultsForOfflineRecognition() {
        verify(speechRecognizerIntent).putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, true);
    }

    private void thenUseGermanAsDefaultLanguage() {
        verify(speechRecognizerIntent).putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);
    }

    private void thenSetPackageNameFromApp() {
        verify(speechRecognizerIntent).putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, packageName);
    }

    private void thenUseFreeFormForMatching() {
        verify(speechRecognizerIntent).putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
    }

    private void thenRequestSpeechRecognition() {
        verify(speechRecognizerIntent).setAction(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    }
}