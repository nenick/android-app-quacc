package de.nenick.quacc.addaccounting;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;

@EBean
public class AddAccountingPresenter {

    RecognizeAccountingTypeUc recognizeAccountingTypeUc = new RecognizeAccountingTypeUc();

    AddAccountingFragment view;

    public void onViewSpeechResult(String recognizedText) {
        String accountingType = recognizeAccountingTypeUc.apply(recognizedText);

        view.showRecognizedText(recognizedText);
    }
}
