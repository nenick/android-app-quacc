package de.nenick.quacc.addaccounting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.SpeechRecognizer;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import de.nenick.quacc.R;
import de.nenick.quacc.datepicker.DatePickerDialogFragment;
import de.nenick.quacc.datepicker.DatePickerFormatUtil;
import de.nenick.quacc.speechrecognition.SpeechRecognitionListener;
import de.nenick.quacc.speechrecognition.SpeechRecognitionWrapper;

@EFragment(R.layout.fragment_add_accounting)
public class AddAccountingFragment extends Fragment {

    public static AddAccountingFragment build() {
        return AddAccountingFragment_.builder().build();
    }

    public static final int REQUEST_DATE_PICKER = 1;

    @ViewById(R.id.account)
    Spinner accountSpinner;

    @ViewById(R.id.accountingType)
    Spinner accountingTypeSpinner;

    @ViewById(R.id.interval)
    Spinner accountingIntervalSpinner;

    @ViewById(R.id.category)
    Spinner accountingCategorySpinner;

    @ViewById(R.id.date)
    TextView date;

    @Bean
    AddAccountingPresenter presenter;

    @Bean
    SpeechRecognitionWrapper speechRecognition;

    @AfterViews
    protected void onAfterViews() {
        presenter.onViewCreated(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        speechRecognition.setRecognitionListener(new SpeechRecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                onSpeechResult(results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION));
            }
        });
    }

    private void onSpeechResult(ArrayList<String> matches) {
        // every time I got only one entry
        if (matches.size() != 1) {
            throw new UnsupportedOperationException("More than one match is not tested");
        }
        presenter.onViewSpeechResult(matches.get(0));
    }

    public void showDate(String dateString) {
        date.setText(dateString);
    }

    @Click(R.id.speechRecognitionBtn)
    protected void onToggleSpeechRecognition() {
        speechRecognition.toggle();
    }

    @Click(R.id.date)
    protected void onShowDatePicker() {
        DatePickerDialogFragment datePickerDialogFragment = new DatePickerDialogFragment();
        datePickerDialogFragment.setTargetFragment(this, REQUEST_DATE_PICKER);
        datePickerDialogFragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DATE_PICKER:
                if (resultCode == Activity.RESULT_OK) {
                    date.setText(DatePickerFormatUtil.fromResultIntent(data));
                }
                break;
            default:
                throw new IllegalStateException("Not handled activity result request.");
        }
    }

    public void showAccounts(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    public void showAccountingTypes(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingTypeSpinner.setAdapter(adapter);
    }

    public void showAccountingIntervals(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingIntervalSpinner.setAdapter(adapter);
    }

    public void showAccountingCategories(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingCategorySpinner.setAdapter(adapter);
    }

    public void showRecognizedText(String recognizedText) {
        ((TextView) getActivity().findViewById(R.id.speechResult)).setText(recognizedText);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        speechRecognition.destroy();
    }
}
