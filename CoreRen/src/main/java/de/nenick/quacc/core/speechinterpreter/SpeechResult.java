package de.nenick.quacc.core.speechinterpreter;

public class SpeechResult {
    public final String value;
    public final int start;
    public final int length;

    public SpeechResult(String value, int start, int length) {
        this.start = start;
        this.length = length;
        this.value = value;
    }
}
