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
import de.nenick.quacc.speechrecognition.SpeechRecognizerApiIntent;

@EFragment(R.layout.fragment_add_accounting)
public class AddAccountingFragment extends Fragment {

    public static final int REQUEST_DATE_PICKER = 1;
    private SpeechRecognizer mSpeechRecognizer;
    private Intent mSpeechRecognizerIntent;
    private boolean mIslistening;

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

    @AfterViews
    protected void onAfterViews() {
        presenter.view = this;
        mSpeechRecognizer = SpeechRecognizer.createSpeechRecognizer(getActivity());
        mSpeechRecognizerIntent = SpeechRecognizerApiIntent.create(getActivity());
        mSpeechRecognizer.setRecognitionListener(new SpeechRecognitionListener() {
            @Override
            public void onResults(Bundle results) {
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                // every time I got only one entry
                if(matches.size() != 1) {
                    throw new UnsupportedOperationException("More than one match is not tested");
                }
                presenter.onViewSpeechResult(matches.get(0));
            }
        });

        initAccountSelection();
        initAccountingTypeSelection();
        initAccountingIntervalSelection();
        initAccountingCategorySelection();
        initDateInformation();

    }

    private void initDateInformation() {
        date.setText(DatePickerFormatUtil.currentDate());
    }

    @Click(R.id.speechRecognitionBtn)
    protected void onButton() {
        if (!mIslistening) {
            mIslistening = true;
            mSpeechRecognizer.startListening(mSpeechRecognizerIntent);
        } else {
            mIslistening = false;
            mSpeechRecognizer.stopListening();
        }
    }

    @Click(R.id.date)
    public void onShowDatePicker() {
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

    private void initAccountSelection() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounts, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    private void initAccountingTypeSelection() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounting_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingTypeSpinner.setAdapter(adapter);
    }

    private void initAccountingIntervalSelection() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounting_intervals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingIntervalSpinner.setAdapter(adapter);
    }

    private void initAccountingCategorySelection() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.accounting_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingCategorySpinner.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mSpeechRecognizer != null) {
            mSpeechRecognizer.destroy();
        }
    }

    public static AddAccountingFragment build() {
        return AddAccountingFragment_.builder().build();
    }

    public void showRecognizedText(String recognizedText) {
        ((TextView) getActivity().findViewById(R.id.speechResult)).setText(recognizedText);
    }
}
