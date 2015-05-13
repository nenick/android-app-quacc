package de.nenick.quacc.accounting.create;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.datepicker.DatePickerDialogWrapper;

@EBean
public class CreateAccountingView extends BaseView {

    @ViewById(R.id.account)
    Spinner accountSpinner;

    @ViewById(R.id.type)
    Spinner accountingTypeSpinner;

    @ViewById(R.id.interval)
    Spinner accountingIntervalSpinner;

    @ViewById(R.id.category)
    Spinner accountingCategorySpinner;

    @ViewById(R.id.date)
    TextView date;

    @ViewById(R.id.value)
    EditText value;

    @ViewById(R.id.valueError)
    TextView valueError;

    @ViewById(R.id.comment)
    EditText comment;

    @ViewById(R.id.btn_speech_recognition)
    FloatingActionButton speechButton;

    @Bean
    DatePickerDialogWrapper datePickerDialog;

    public void showAccounts(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    public String getAccount() {
        return accountSpinner.getSelectedItem().toString();
    }

    public void showAccountingTypes(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingTypeSpinner.setAdapter(adapter);
    }

    public String getAccountingType() {
        return accountingTypeSpinner.getSelectedItem().toString();
    }

    public void showAccountingIntervals(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingIntervalSpinner.setAdapter(adapter);
    }

    public String getAccountingInterval() {
        return accountingIntervalSpinner.getSelectedItem().toString();
    }

    public void showAccountingCategories(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountingCategorySpinner.setAdapter(adapter);
    }

    public String getAccountingCategory() {
        return accountingCategorySpinner.getSelectedItem().toString();
    }

    public void showRecognizedText(String recognizedText) {
        ((TextView) context.findViewById(R.id.speechResult)).setText(recognizedText);
    }

    public void showDate(String dateString) {
        date.setText(dateString);
    }

    public String getDate() {
        return date.getText().toString();
    }

    public String getValue() {
        return value.getText().toString();
    }

    public String getComment() {
        return comment.getText().toString();
    }

    public void showValueParsingError(int resourceId) {
        valueError.setText(resourceId);
    }

    @Click(R.id.date)
    void onShowDatePicker() {
        datePickerDialog.start(new DatePickerDialogWrapper.Callback() {
            @Override
            public void onDatePick(String date) {
                showDate(date);
            }
        });
    }

    public void showSpeechStartButton() {
        speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_mic));
    }

    public void showSpeechStopButton() {
        speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_micoff));
    }
}
