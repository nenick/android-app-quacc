package de.nenick.quacc.speechrecognition;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;

import java.util.ArrayList;

/**
 * Listener wrapper for the SpeechRecognizer.
 * <p/>
 * Use hack for offline speech recognition. Same usage for offline and online speech recognition.
 * http://stackoverflow.com/questions/30654191/speechrecognizer-offline-error-no-match
 * <p/>
 * Empty implementation for unused callback methods. Other implementations must not implement
 * them for more readable code.
 */
public class QuAccSpeechRecognizerListener implements RecognitionListener {

    private ArrayList<String> recognitionResults = new ArrayList<>();
    private ArrayList<String> recognitionResultsLastPart = new ArrayList<>();
    private SpeechResultListener speechResultListener;
    private boolean alreadyErrorReported = false;

    public QuAccSpeechRecognizerListener(SpeechResultListener speechResultListener) {
        this.speechResultListener = speechResultListener;
    }

    @Override
    public void onResults(Bundle results) {
        recognitionResults = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        speechResultListener.onResults(recognitionResults);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        // Save last partial results for offline speech recognition.
        recognitionResults = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        recognitionResultsLastPart = partialResults.getStringArrayList("android.speech.extra.UNSTABLE_TEXT");
    }

    @Override
    public void onError(int error) {
        if (isOfflineSpeechRecognition(error)) {
            if (alreadyErrorReported) {
                // when offline we get ERROR_NO_MATCH reported twice
                return;
            }
            alreadyErrorReported = true;

            // Partial results are split up by previous spoken text and the text part after last
            // short speak break. It gives only one result when doing speech recognition offline.
            appendLastRecognizedTextPartToRecognitionResults();
            speechResultListener.onResults(recognitionResults);
        } else {
            speechResultListener.onError(error);
        }
    }

    private boolean isOfflineSpeechRecognition(int error) {
        return error == SpeechRecognizer.ERROR_NO_MATCH && existPartialResults();
    }

    @Override
    public void onBeginningOfSpeech() {
        alreadyErrorReported = false;
        recognitionResults = new ArrayList<>();
        recognitionResultsLastPart = new ArrayList<>();
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        // current no usage
    }

    @Override
    public void onEndOfSpeech() {
        // current no usage
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        // current no usage
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        // current no usage
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        // current no usage
    }

    private boolean existPartialResults() {
        return recognitionResults.size() > 0;
    }

    private void appendLastRecognizedTextPartToRecognitionResults() {
        recognitionResults.set(0, recognitionResults.get(0) + " " + recognitionResultsLastPart.get(0));
    }
}
