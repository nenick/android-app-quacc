package de.nenick.quacc.speechrecognition;

import org.androidannotations.annotations.EBean;

import java.util.List;

@EBean
public class RecognizeAccountingTypeFunction {

    public String apply(List<String> recognizedText) {
        return recognizedText.get(0);
    }

}
