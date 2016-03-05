package de.nenick.quacc.speechrecognition.hotword;

import android.os.Bundle;
import android.speech.RecognitionListener;

public abstract class RecognitionListenerWithHiddenUnusedMethods implements RecognitionListener {

    @Override
    public void onBeginningOfSpeech() {

    }

    @Override
    public void onRmsChanged(float rmsdB) {

    }

    @Override
    public void onBufferReceived(byte[] buffer) {

    }

    @Override
    public void onEndOfSpeech() {

    }

    @Override
    public void onEvent(int eventType, Bundle params) {

    }
}
