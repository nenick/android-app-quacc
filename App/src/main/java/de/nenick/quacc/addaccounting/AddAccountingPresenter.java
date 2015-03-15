package de.nenick.quacc.addaccounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.R;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.daggersupport.DaggerSupport;
import de.nenick.quacc.datepicker.DatePickerFormatUtil;

@EBean
public class AddAccountingPresenter {

    @Inject
    RecognizeAccountingTypeUc recognizeAccountingTypeUc;

    AddAccountingFragment view;

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }

    public void onViewSpeechResult(String recognizedText) {
        String accountingType = recognizeAccountingTypeUc.apply(recognizedText);

        view.showRecognizedText(recognizedText);
    }

    public void onViewCreated(AddAccountingFragment view) {
        this.view = view;
        view.showAccounts(R.array.accounts);
        view.showAccountingTypes(R.array.accounting_types);
        view.showAccountingIntervals(R.array.accounting_intervals);
        view.showAccountingCategorys(R.array.accounting_categories);
        view.showDate(DatePickerFormatUtil.currentDate());
    }
}
