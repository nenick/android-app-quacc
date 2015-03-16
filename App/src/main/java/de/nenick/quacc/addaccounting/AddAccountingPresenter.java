package de.nenick.quacc.addaccounting;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EBean;

import javax.inject.Inject;

import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.daggersupport.DaggerSupport;
import de.nenick.quacc.datepicker.DatePickerFormatUtil;

@EBean
public class AddAccountingPresenter {

    AddAccountingFragment view;

    @Inject
    RecognizeAccountingTypeUc recognizeAccountingTypeUc;

    @Inject
    GetAccountingCategoriesUc getAccountingCategoriesUc;

    @Inject
    GetAccountingIntervalsUc getAccountingIntervalsUc;

    @Inject
    GetAccountingTypesUc getAccountingTypesUc;

    @Inject
    GetAccountsUc getAccountsUc;

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
        view.showAccounts(getAccountsUc.apply());
        view.showAccountingTypes(getAccountingTypesUc.apply());
        view.showAccountingIntervals(getAccountingIntervalsUc.apply());
        view.showAccountingCategories(getAccountingCategoriesUc.apply());
        view.showDate(DatePickerFormatUtil.currentDate());
    }
}
