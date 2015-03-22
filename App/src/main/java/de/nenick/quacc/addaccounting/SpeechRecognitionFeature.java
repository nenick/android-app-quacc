package de.nenick.quacc.addaccounting;

import android.content.Context;
import android.os.Bundle;
import android.speech.SpeechRecognizer;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.SpeechListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @RootContext
    Context context;

    @ViewById(R.id.btn_speech_recognition)
    FloatingActionButton speechButton;

    @Bean
    SpeechRecognitionWrapper speechRecognitionWrapper;

    AddAccountingFragment view;
    AddAccountingPresenter presenter;

    public void setHandler(AddAccountingFragment view, AddAccountingPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public void destroy() {
        speechRecognitionWrapper.destroy();
    }

    @AfterInject
    void onAfterInject() {
        speechRecognitionWrapper.setSpeechListener(new SpeechListener() {
            @Override
            public void onError(int error) {
                toggleIsListeningMarker();
            }

            @Override
            public void onResults(Bundle results) {
                presenter.onViewSpeechResult(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
                toggleIsListeningMarker();
            }
        });
    }

    @Click(R.id.btn_speech_recognition)
    protected void onToggleSpeechRecognition() {
        speechRecognitionWrapper.toggle();
        toggleIsListeningMarker();
    }

    private void toggleIsListeningMarker() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if(isListening) {
            speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_mic));
        } else {
            speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_micoff));
        }
    }

    public void onPause() {
        boolean isListening = speechRecognitionWrapper.isListening();
        if(isListening) {
            onToggleSpeechRecognition();
        }
    }
}
