package de.nenick.quacc.speechrecognition.hotword;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import de.nenick.quacc.speechrecognition.speech.QuAccSpeechRecognizer;

/**
 * Wrapper for the android speech recognition.
 * <p/>
 * Works online and offline but offline its less effective and reports only one variant.
 */
@EBean
public class QuAccHotwordRecognizer {

    @Bean
    protected QuAccSpeechRecognizer speechRecognizer;

    private HotwordListener hotwordListener;

    private String[] hotwords = {};

    /**
     * The value 200 ms was tested successful on Nexus 6 with Android M. With values below the speech recognition dies silently after a while.
     */
    private static final int PAUSE_BEFORE_RESTART_SPEECH_RECOGNITION = 200;

    public void setHotwordListener(final HotwordListener listener, String ... hotwords) {
        this.hotwords = hotwords;
        hotwordListener = listener;
        speechRecognizer.setRecognitionListener(new RecognitionListenerWithHiddenUnusedMethods() {

            private boolean hotwordNeedsToBeReported;

            @Override
            public void onReadyForSpeech(Bundle params) {
                hotwordNeedsToBeReported = true;
            }

            @Override
            public void onResults(Bundle results) {
                if(hotwordNeedsToBeReported) {
                    String hotword = searchForHotword(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
                    if (hotword != null) {
                        hotwordListener.onHotword(hotword);
                    }
                }
                restartSpeechRecognition();
            }

            @Override
            public void onPartialResults(Bundle partialResults) {
                String hotword = searchForHotword(partialResults.getStringArrayList("android.speech.extra.UNSTABLE_TEXT"));
                if (hotword != null) {
                    hotwordNeedsToBeReported = false;
                    hotwordListener.onHotword(hotword);
                }
            }

            @Override
            public void onError(int errorCode) {
                if (isErrorBecauseNothingHeard(errorCode)) {
                    restartSpeechRecognition();
                } else {
                    hotwordListener.onError(errorCode);
                }
            }
        });
    }

    private boolean isErrorBecauseNothingHeard(int errorCode) {
        return (errorCode == SpeechRecognizer.ERROR_NO_MATCH) || (errorCode == SpeechRecognizer.ERROR_SPEECH_TIMEOUT);
    }

    private void restartSpeechRecognition() {
        // restarting speech recognition can end in silent die when called to fast after an error
        try {
            Thread.sleep(PAUSE_BEFORE_RESTART_SPEECH_RECOGNITION);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        startListening();
    }

    public void startListening() {
        speechRecognizer.startListening();
    }

    public void stopListening() {
        speechRecognizer.stopListening();
    }

    public void destroy() {
        speechRecognizer.destroy();
    }

    private String searchForHotword(List<String> heard) {
        for (String variant : heard) {
            for (String hotword : hotwords) {
                if (variant.toLowerCase().contains(hotword.toLowerCase())) {
                    return hotword;
                }
            }
        }
        return null;
    }
}
