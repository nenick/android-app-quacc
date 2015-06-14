package de.nenick.quacc.view.accounting_create;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;
import de.nenick.quacc.view.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @RootContext
    Context context;

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Bean
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

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
            }

            @Override
            public void onResults(ArrayList<String> speechResults) {
                interpreteSpeechResult(speechResults);
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

    private void interpreteSpeechResult(ArrayList<String> matches) {
        String accountingType = recognizeAccountingTypeFunction.apply(matches);

        String recognizedText = "";
        for (String match : matches) {
            recognizedText += "[" + match + "] ";
        }
        view.showRecognizedText(recognizedText);
    }
}
