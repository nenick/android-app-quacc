package de.nenick.quacc.addaccounting;

import android.app.Activity;
import android.content.Intent;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.ArrayList;

import javax.inject.Inject;

import de.nenick.quacc.core.accounting.GetAccountingCategoriesUc;
import de.nenick.quacc.core.accounting.GetAccountingIntervalsUc;
import de.nenick.quacc.core.accounting.GetAccountingTypesUc;
import de.nenick.quacc.core.accounting.GetAccountsUc;
import de.nenick.quacc.core.speechinterpretation.RecognizeAccountingTypeUc;
import de.nenick.quacc.dagger.DaggerSupport;
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

    @Bean
    DatePickerFormatUtil datePickerFormatUtil;

    @AfterInject
    protected void afterInject() {
        DaggerSupport.inject(this);
    }

    public void onViewSpeechResult(ArrayList<String> matches) {
        if (matches == null || matches.size() < 1) {
            throw new UnsupportedOperationException("Getting no match was never tested");
        }

        String accountingType = recognizeAccountingTypeUc.apply(matches);

        String recognizedText = "";
        for (String match : matches) {
            recognizedText += "[" + match + "] ";
        }
        view.showRecognizedText(recognizedText);
    }

    public void onViewCreated(AddAccountingFragment view) {
        this.view = view;
        view.showAccounts(getAccountsUc.apply());
        view.showAccountingTypes(getAccountingTypesUc.apply());
        view.showAccountingIntervals(getAccountingIntervalsUc.apply());
        view.showAccountingCategories(getAccountingCategoriesUc.apply());
        view.showDate(datePickerFormatUtil.currentDate());
    }

    public void onPicketDate(int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            view.setDate(datePickerFormatUtil.fromResultIntent(data));
        }
    }
}
