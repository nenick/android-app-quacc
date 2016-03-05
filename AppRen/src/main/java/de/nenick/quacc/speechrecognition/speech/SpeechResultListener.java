package de.nenick.quacc.speechrecognition.speech;

import java.util.ArrayList;

public interface SpeechResultListener {

    void onError(int error);

    void onResults(ArrayList<String> speechResults);
}
