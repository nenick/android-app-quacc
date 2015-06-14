package de.nenick.quacc.accounting.create.speechinterpretation;

import org.junit.Test;

import java.util.ArrayList;

import de.nenick.quacc.speechrecognition.RecognizeAccountingTypeFunction;

import static org.assertj.core.api.Assertions.assertThat;

public class RecognizeAccountingTypeFunctionTest {

    RecognizeAccountingTypeFunction recognizeAccountingTypeFunction = new RecognizeAccountingTypeFunction();

    @Test
    public void testApply() throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Blub");
        String apply = recognizeAccountingTypeFunction.apply(strings);
        assertThat(apply).isEqualTo("Blub");
    }
}