package de.nenick.quacc.speechrecognition;

import org.androidannotations.annotations.EBean;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@EBean
public class RecognizeValueFunction {

    public SpeechResult apply(String recognizedText) {

        int euroStart = 0;
        int euroNumber = 0;
        int euroNumberStart = 0;
        if (recognizedText.toLowerCase().contains("Euro".toLowerCase())) {
            euroStart = recognizedText.indexOf("Euro");
        }

        int centStart = 0;
        int centNumber = 0;
        int centNumberStart = 0;
        if (recognizedText.toLowerCase().contains("Cent".toLowerCase())) {
            centStart = recognizedText.indexOf("Cent");
        }

        if (euroStart == 0 && centStart == 0) {
            return null;
        }

        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(recognizedText);
        while (m.find()) {
            String numberString = m.group();
            int number = Integer.parseInt(numberString);
            int numberIndex = recognizedText.indexOf(numberString);

            if (numberIndex < euroStart) {
                euroNumber = number;
                euroNumberStart = numberIndex;
            } else if (numberIndex < centStart) {
                centNumber = number;
                centNumberStart = numberIndex;
            }
        }

        String centString = ",";
        if (centNumber < 10) {
            centString += "0" + centNumber;
        } else {
            centString += centNumber;
        }

        String value = euroNumber + centString;
        if (euroStart > 0 && centStart > 0) {
            return new SpeechResult(value, euroNumberStart, centStart + "cent".length() - euroNumberStart);
        } if (euroStart > 0) {
            return new SpeechResult(value, euroNumberStart, euroStart + "euro".length() - euroNumberStart);
        } else {
            return new SpeechResult(value, centNumberStart, centStart + "cent".length() - centNumberStart);
        }
    }

}
