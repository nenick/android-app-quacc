package de.nenick.quacc.core.speechinterpretation;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class RecognizeAccountingTypeUc {

    public String apply(List<String> recognizedText) {
        return recognizedText.get(0);
    }

}
