package de.nenick.quacc.view.accounting_edit.speechrecognition;

import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.QuAccSpeechRecognizer;
import de.nenick.quacc.speechrecognition.SpeechResultListener;
import de.nenick.quacc.view.accounting_edit.EditAccountingView;

@EBean
public class SpeechRecognitionFeature {

    @Bean
    QuAccSpeechRecognizer quAccSpeechRecognizer;

    @Bean
    InterpretTemplateFunction interpretTemplateFunction;

    @Bean
    InterpretSpeechFunction interpretSpeechFunction;

    EditAccountingView view;

    public void setView(EditAccountingView view) {
        this.view = view;
    }

    public void onViewFinish() {
        quAccSpeechRecognizer.destroy();
    }

    public void onViewPause() {
        quAccSpeechRecognizer.stopListening();
        view.showSpeechStartButton();
    }

    @AfterInject
    public void onAfterInject() {
        quAccSpeechRecognizer.setSpeechResultListener(new SpeechResultListener() {
            @Override
            public void onError(int error) {
                view.showSpeechStartButton();
                if (error == SpeechRecognizer.ERROR_NO_MATCH) {
                    view.showSpeechError("Spracherkung Fehler: kein Text gesprochen. (Kann bei einigen Geräten oder Android Versionen auftreten, wenn man Offline ist.)");
                } else {
                    view.showSpeechError("Spracherkung Fehler: " + error + ".");
                }

            }

            @Override
            public void onResults(ArrayList<String> speechResults) {
                interpretSpeechResult(speechResults);
                view.showSpeechStartButton();
            }
        });
    }

    @Click(R.id.btn_speech_recognition)
    public void onToggleSpeechRecognitionClick() {
        if (!quAccSpeechRecognizer.isSpeechRecognitionAvailable()) {
            view.showToast(R.string.speech_recognition_not_available);
            return;
        }
        if (quAccSpeechRecognizer.isListening()) {
            quAccSpeechRecognizer.stopListening();
            view.showSpeechStartButton();
        } else {
            quAccSpeechRecognizer.startListening();
            view.showSpeechStopButton();
        }
    }

    private void interpretSpeechResult(ArrayList<String> matches) {
        showRecognizedTextAsHint(matches);
        if (interpretTemplateFunction.apply(view, matches)) {
            return;
        }
        interpretSpeechFunction.apply(view, matches);
    }

    private void showRecognizedTextAsHint(ArrayList<String> matches) {
        String recognizedText = "";
        for (String match : matches) {
            recognizedText += "[" + match + "] ";
        }
        view.showRecognizedText(recognizedText);
    }


}
