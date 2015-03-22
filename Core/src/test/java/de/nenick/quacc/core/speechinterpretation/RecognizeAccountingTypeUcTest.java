package de.nenick.quacc.core.speechinterpretation;

import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class RecognizeAccountingTypeUcTest {

    RecognizeAccountingTypeUc recognizeAccountingTypeUc = new RecognizeAccountingTypeUc();

    @Test
    public void testApply() throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        strings.add("Blub");
        String apply = recognizeAccountingTypeUc.apply(strings);
        assertThat(apply).isEqualTo("Blub");
    }
}