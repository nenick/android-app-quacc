package de.nenick.quacc.speechrecognition;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;

public class SpeechRecognizerApiIntent {

    public static Intent create(Context context) {
        Intent mSpeechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        mSpeechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, context.getPackageName());
        return mSpeechRecognizerIntent;
    }
}
