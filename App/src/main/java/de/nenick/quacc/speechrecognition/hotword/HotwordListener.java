package de.nenick.quacc.speechrecognition.hotword;

public interface HotwordListener {

    void onError(int error);

    void onHotword(String hotword);
}
