package de.nenick.quacc.speechinterpreter;

import org.junit.Test;

import de.nenick.quacc.speechinterpreter.RecognizeValueFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class RecognizeValueFunctionTest {

    RecognizeValueFunction recognizeValueFunction = new RecognizeValueFunction();

    @Test
    public void parseEuroAndCent() {
        String euro = "5 Euro ";
        String cent = "20 Cent";

        String recognizedText = euro + cent;
        RecognizeValueFunction.SpeechValueResult result = recognizeValueFunction.apply(recognizedText);
        assertThat(result.value).isEqualTo(520);
        assertThat(result.length).isEqualTo((euro + cent).length());
    }

    @Test
    public void parseEuroAndCentWithLeadingText() {
        String euro = "300 Euro ";
        String cent = "20 Cent";
        String leadingText = "comment ";

        String recognizedText = leadingText + euro + cent;
        RecognizeValueFunction.SpeechValueResult result = recognizeValueFunction.apply(recognizedText);
        assertThat(result.value).isEqualTo(30020);
        assertThat(result.start).isEqualTo(leadingText.length());
        assertThat(result.length).isEqualTo((euro + cent).length());
    }

}