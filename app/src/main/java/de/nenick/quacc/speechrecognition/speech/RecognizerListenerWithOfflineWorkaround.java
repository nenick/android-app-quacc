package de.nenick.quacc.speechrecognition.speech;

import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.util.Log;

import java.util.ArrayList;

/**
 * Wrapper for easy offline speech recognition support.
 * <p/>
 * When offline the speech recognizer will not more end with ERROR_NO_MATCH when text was spoken.
 * Instead it is reported like online recognized text with SpeechRecognizer.RESULTS_RECOGNITION.
 * <p/>
 * The difference between online and offline is that you only get one match in the results.
 * <p/>
 * See also http://stackoverflow.com/questions/30654191/speechrecognizer-offline-error-no-match
 */
public class RecognizerListenerWithOfflineWorkaround implements RecognitionListener {

    private RecognitionListener wrappedRecognitionListener;

    /**
     * Save the latest partial result for reporting when offline.
     */
    private Bundle latestPartialResults = null;

    /**
     * The onError method get called multiple times with the same errorCode when offline
     * but it is enough to report it only once.
     */
    private boolean isErrorAlreadyCalledAfterBeginOfSpeech = false;

    public RecognizerListenerWithOfflineWorkaround(RecognitionListener recognitionListener) {
        wrappedRecognitionListener = recognitionListener;
    }

    @Override
    public void onReadyForSpeech(Bundle params) {
        Log.d(getClass().getName(), "onReadyForSpeech");
        latestPartialResults = null;
        isErrorAlreadyCalledAfterBeginOfSpeech = false;
        wrappedRecognitionListener.onReadyForSpeech(params);
    }

    @Override
    public void onResults(Bundle results) {
        wrappedRecognitionListener.onResults(results);
    }

    @Override
    public void onPartialResults(Bundle partialResults) {
        latestPartialResults = partialResults;
        wrappedRecognitionListener.onPartialResults(partialResults);
    }

    @Override
    public void onError(int error) {
        if (isErrorAlreadyCalledAfterBeginOfSpeech) {
            return;
        }
        isErrorAlreadyCalledAfterBeginOfSpeech = true;

        if (isOfflineSpeechRecognition(error, latestPartialResults)) {
            appendUnstableTextToResult(latestPartialResults);
            wrappedRecognitionListener.onResults(latestPartialResults);
        } else {
            wrappedRecognitionListener.onError(error);
        }
    }

    private boolean isOfflineSpeechRecognition(int error, Bundle latestPartialResults) {
        return error == SpeechRecognizer.ERROR_NO_MATCH && latestPartialResults != null;
    }

    private void appendUnstableTextToResult(Bundle partialResults) {
        // Partial results are split up by previous spoken text and the current spoken text after
        // short speak break given as 'UNSTABLE_TEXT'. To provide same usage for online and offline
        // we report it combined. It gives only one result when doing speech recognition offline.
        ArrayList<String> target = partialResults.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        ArrayList<String> source = partialResults.getStringArrayList("android.speech.extra.UNSTABLE_TEXT");
        target.set(0, (target.get(0) + " " + source.get(0)).trim());
    }

    @Override
    public void onBeginningOfSpeech() {
        latestPartialResults = null;
        wrappedRecognitionListener.onBeginningOfSpeech();
    }

    @Override
    public void onBufferReceived(byte[] buffer) {
        wrappedRecognitionListener.onBufferReceived(buffer);
    }

    @Override
    public void onEndOfSpeech() {
        wrappedRecognitionListener.onEndOfSpeech();
    }

    @Override
    public void onEvent(int eventType, Bundle params) {
        wrappedRecognitionListener.onEvent(eventType, params);
    }

    @Override
    public void onRmsChanged(float rmsdB) {
        wrappedRecognitionListener.onRmsChanged(rmsdB);
    }
}
