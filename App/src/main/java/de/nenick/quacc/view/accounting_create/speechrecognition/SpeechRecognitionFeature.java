package de.nenick.quacc.view.accounting_create.speechrecognition;

import android.content.Context;
import android.speech.SpeechRecognizer;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;
import java.util.List;

import de.nenick.quacc.R;
import de.nenick.quacc.StringPartUtil;
import de.nenick.quacc.speechrecognition.RecognizeAccountingIntervalFunction;
import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;
import de.nenick.quacc.speechrecognition.RecognizeCategoryFunction;
import de.nenick.quacc.speechrecognition.RecognizeTemplateFunction;
import de.nenick.quacc.speechrecognition.RecognizeValueFunction;
import de.nenick.quacc.speechrecognition.SpeechResult;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;
import de.nenick.quacc.valueparser.ParseValueFromIntegerFunction;
import de.nenick.quacc.view.accounting_create.CreateAccountingView;

@EBean
public class SpeechRecognitionFeature {

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Bean
    InterpretTemplateFunction interpretTemplateFunction;

    @Bean
    InterpretSpeechFunction interpretSpeechFunction;

    CreateAccountingView view;

    public void setView(CreateAccountingView view) {
        this.view = view;
    }

    public void onViewFinish() {
        speechRecognitionWrapper.destroy();
    }

    public void onViewPause() {
        speechRecognitionWrapper.stopListening();
        view.showSpeechStartButton();
    }

    @AfterInject
    public void onAfterInject() {
        speechRecognitionWrapper.setSpeechResultListener(new SpeechRecognitionWrapper.SpeechResultListener() {
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
        if (!speechRecognitionWrapper.isSpeechRecognitionAvailable()) {
            view.showToast(R.string.speech_recognition_not_available);
            return;
        }
        if (speechRecognitionWrapper.isListening()) {
            speechRecognitionWrapper.stopListening();
            view.showSpeechStartButton();
        } else {
            speechRecognitionWrapper.startListening();
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
