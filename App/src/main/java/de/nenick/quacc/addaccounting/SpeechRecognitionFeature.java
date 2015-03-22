package de.nenick.quacc.addaccounting;

import android.os.Bundle;
import android.speech.SpeechRecognizer;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.speechrecognition.SpeechRecognitionListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

@EBean
public class SpeechRecognitionFeature {

    @ViewById(R.id.btn_speech_recognition)
    FloatingActionButton speechButton;

    @Bean
    SpeechRecognitionWrapper speechRecognition;

    AddAccountingFragment view;
    AddAccountingPresenter presenter;

    public void setHandler(AddAccountingFragment view, AddAccountingPresenter presenter) {
        this.view = view;
        this.presenter = presenter;
    }

    public void destroy() {
        speechRecognition.destroy();
    }

    @AfterInject
    protected void onAfterInject() {
        speechRecognition.setRecognitionListener(new SpeechRecognitionListener() {
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
        speechRecognition.toggle();
        toggleIsListeningMarker();
    }

    private void toggleIsListeningMarker() {
        boolean isListening = speechRecognition.isListening();
        if(isListening) {
            speechButton.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_action_mic));
        } else {
            speechButton.setImageDrawable(view.getResources().getDrawable(R.drawable.ic_action_micoff));
        }
    }
}
