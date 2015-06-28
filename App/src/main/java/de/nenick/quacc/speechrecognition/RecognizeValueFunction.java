package de.nenick.quacc.speechrecognition;

import org.androidannotations.annotations.EBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EBean
public class RecognizeValueFunction {

    Pattern euroPattern = Pattern.compile("[0-9]+ +euro");
    Pattern centPattern = Pattern.compile("[0-9]+ +cent");

    public static class SpeechValueResult {
        public final int value;
        public final int start;
        public final int length;

        public SpeechValueResult(int value, int start, int length) {
            this.start = start;
            this.length = length;
            this.value = value;
        }
    }

    public SpeechValueResult apply(String recognizedText) {
        String recognizedTextLowerCase = recognizedText.toLowerCase();

        String euroString = extractEuroStringFrom(recognizedTextLowerCase);
        String centString = extractCentStringFrom(recognizedTextLowerCase);
        if (euroString == null && centString == null) {
            return null;
        }

        int euroValue = extractEuroValue(euroString);
        int centValue = extractCentValue(centString);
        int value = calculateValueBasedOnCent(euroValue, centValue);

        if (euroString != null && centString != null) {
            return new SpeechValueResult(value, recognizedTextLowerCase.indexOf(euroString), lengthFromEuroStartToCentEnd(recognizedTextLowerCase, euroString, centString));
        } if (euroString != null) {
            return new SpeechValueResult(value, recognizedTextLowerCase.indexOf(euroString), euroString.length());
        } else {
            return new SpeechValueResult(value, recognizedTextLowerCase.indexOf(centString), centString.length());
        }
    }

    private int calculateValueBasedOnCent(int euroValue, int centValue) {
        int value = euroValue * 100;
        value += centValue;
        return value;
    }

    private int extractCentValue(String centString) {
        int centValue = 0;
        if (foundMatchFor(centString)) {
            String centWithoutLabel = centString.replace("cent", "").trim();
            centValue = Integer.parseInt(centWithoutLabel);
        }
        return centValue;
    }

    private int extractEuroValue(String euroString) {
        int euroValue = 0;
        if (foundMatchFor(euroString)) {
            String euroWithoutLabel = euroString.replace("euro", "").trim();
            euroValue = Integer.parseInt(euroWithoutLabel);
        }
        return euroValue;
    }

    private int lengthFromEuroStartToCentEnd(String recognizedTextLowerCase, String euroString, String centString) {
        return recognizedTextLowerCase.indexOf(centString) + centString.length() - recognizedTextLowerCase.indexOf(euroString);
    }

    private boolean foundMatchFor(String euroString) {
        return euroString != null;
    }

    private String extractEuroStringFrom(String recognizedText) {
        Matcher matcher = euroPattern.matcher(recognizedText.toLowerCase());
        if(notMatch(matcher)) {
            return null;
        }
        return matcher.group();
    }

    private String extractCentStringFrom(String recognizedText) {
        Matcher matcher = centPattern.matcher(recognizedText.toLowerCase());
        if(notMatch(matcher)) {
            return null;
        }
        return matcher.group();
    }


    private boolean notMatch(Matcher matcher) {
        return !matcher.find();
    }
}
