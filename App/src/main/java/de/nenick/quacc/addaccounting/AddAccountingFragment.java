package de.nenick.quacc.addaccounting;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.datepicker.DatePickerDialogFragment;
import de.nenick.quacc.datepicker.DatePickerDialogFragment_;

@EFragment(R.layout.fragment_add_accounting)
public class AddAccountingFragment extends Fragment {

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
    SpeechRecognitionFeature speechRecognitionFeature;

    public static AddAccountingFragment build() {
        return AddAccountingFragment_.builder().build();
    }

    @AfterViews
    protected void onAfterViews() {
        presenter.onViewCreated(this);
        speechRecognitionFeature.setHandler(this, presenter);
        ((SpeechRecognitionFeature_) speechRecognitionFeature).rebind(getActivity());
    }

    @Override
    public void onPause() {
        super.onPause();
        speechRecognitionFeature.onPause();
    }

    public void showDate(String dateString) {
        date.setText(dateString);
    }

    @Click(R.id.date)
    protected void onShowDatePicker() {
        DatePickerDialogFragment datePickerDialogFragment = DatePickerDialogFragment_.builder().build();
        datePickerDialogFragment.setTargetFragment(this, REQUEST_DATE_PICKER);
        datePickerDialogFragment.show(getActivity().getSupportFragmentManager(), "Date Picker");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_DATE_PICKER:
                presenter.onPicketDate(resultCode, data);
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
        speechRecognitionFeature.destroy();
    }

    public void setDate(String date) {
        this.date.setText(date);
    }
}
