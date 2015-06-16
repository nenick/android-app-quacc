package de.nenick.quacc.accounting.create.speechinterpretation;

import org.junit.Test;

import java.util.ArrayList;

import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;
import de.nenick.quacc.speechrecognition.SpeechResult;

import static org.assertj.core.api.Assertions.assertThat;

public class RecognizeAccountingTypeFunctionTest {

    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction = new RecognizeAccountingTypeFunction();

    @Test
    public void testApply() throws Exception {
        SpeechResult apply = recognizeAccountingTypeFunction.apply("Blub");
        assertThat(apply.value).isEqualTo("Blub");
    }
}