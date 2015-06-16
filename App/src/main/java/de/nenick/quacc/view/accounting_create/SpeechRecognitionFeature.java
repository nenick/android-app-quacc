package de.nenick.quacc.view.accounting_create;

import android.content.Context;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.util.ArrayList;

import de.nenick.quacc.R;
import de.nenick.quacc.database.AccountingType;
import de.nenick.quacc.speechrecognition.RecognizeAccountingIntervalFunction;
import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;
import de.nenick.quacc.speechrecognition.RecognizeCategoryFunction;
import de.nenick.quacc.speechrecognition.RecognizeValueFunction;
import de.nenick.quacc.speechrecognition.SpeechResult;
import de.nenick.quacc.view.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @RootContext
    Context context;

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    @Bean
    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction;

    @Bean
    RecognizeAccountingIntervalFunction recognizeAccountingIntervalFunction;

    @Bean
    RecognizeCategoryFunction recognizeCategoryFunction;

    @Bean
    RecognizeValueFunction recognizeValueFunction;

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

        // analyse more than one result is not implemented at this time
        String notMatchedText = matches.get(0);

        notMatchedText = interpretAccountingType(notMatchedText);
        notMatchedText = interpretAccountingInterval(notMatchedText);
        notMatchedText = interpretAccountingCategory(notMatchedText);
        notMatchedText = interpretAccountingValue(notMatchedText);
        if(!notMatchedText.trim().isEmpty()) {
            view.setComment(notMatchedText);
        }

        String recognizedText = "[" + matches.get(0) + "]";
        // analyse more than one result is not implemented at this time
        //for (String match : matches) {
        //    recognizedText += "[" + match + "] ";
        //}
        view.showRecognizedText(recognizedText);
    }

    private String interpretAccountingCategory(String matches) {
        SpeechResult result = recognizeCategoryFunction.apply(matches);
        if(result != null) {
            view.setAccountingCategory(result.value);
            return matches.replace(matches.substring(result.start, result.start + result.length), "").replaceAll("  ", " ");
        }
        return matches;
    }

    private String interpretAccountingType(String matches) {
        SpeechResult result = recognizeAccountingTypeFunction.apply(matches);
        if (result != null) {
            view.setAccountingType(result.value);
            return matches.replace(matches.substring(result.start, result.start + result.length), "").replaceAll("  ", " ");
        }
        return matches;
    }

    private String interpretAccountingInterval(String matches) {
        SpeechResult result = recognizeAccountingIntervalFunction.apply(matches);
        if (result != null) {
            view.setAccountingInterval(result.value);
            return matches.replace(matches.substring(result.start, result.start + result.length), "").replaceAll("  ", " ");
        }
        return matches;
    }

    private String interpretAccountingValue(String matches) {
        SpeechResult result = recognizeValueFunction.apply(matches);
        if (result != null) {
            view.setValue(result.value);
            return matches.replace(matches.substring(result.start, result.start + result.length), "").replaceAll("  ", " ");
        }
        return matches;
    }
}
