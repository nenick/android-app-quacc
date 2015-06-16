package de.nenick.quacc.speechrecognition;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RecognizeValueFunctionTest {

    RecognizeValueFunction recognizeValueFunction = new RecognizeValueFunction();

    @Test
    public void parseEuroAndCentWithLeadingText() {
        String euro = "300 Euro ";
        String cent = "20 Cent";
        String leadingText = "comment ";

        String recognizedText = leadingText + euro + cent;
        SpeechResult result = recognizeValueFunction.apply(recognizedText);
        assertThat(result.value).isEqualTo("300,20");
        assertThat(result.start).isEqualTo(leadingText.length());
        assertThat(result.length).isEqualTo((euro + cent).length());
    }

}