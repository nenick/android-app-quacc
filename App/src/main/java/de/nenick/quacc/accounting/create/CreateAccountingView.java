package de.nenick.quacc.accounting.create;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.common.mvp.BaseView;
import de.nenick.quacc.datepicker.DatePickerDialogWrapper;
import de.nenick.quacc.datepicker.DatePickerDialogWrapper_;

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

    @ViewById(R.id.endDate)
    TextView endDate;

    @ViewById(R.id.endDateLabel)
    TextView endDateLabel;

    @ViewById(R.id.endDateCheck)
    CheckBox endDateCheck;

    public void setAccounts(CharSequence[] stringArray) {
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, stringArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        accountSpinner.setAdapter(adapter);
    }

    public String getAccount() {
        return accountSpinner.getSelectedItem().toString();
    }

    public void setAccountingTypes(SpinnerAdapter adapter) {
        accountingTypeSpinner.setAdapter(adapter);
    }

    public  <T> T getAccountingType() {
        //noinspection unchecked the caller should now what kind of item he expect
        return (T) accountingTypeSpinner.getSelectedItem();
    }

    public void setAccountingIntervals(SpinnerAdapter adapter) {
        accountingIntervalSpinner.setAdapter(adapter);
    }

    public <T> T getAccountingInterval() {
        //noinspection unchecked the caller should now what kind of item he expect
        return (T) accountingIntervalSpinner.getSelectedItem();
    }

    public void setAccountingCategories(SpinnerAdapter adapter) {
        accountingCategorySpinner.setAdapter(adapter);
    }

    public String getAccountingCategory() {
        return accountingCategorySpinner.getSelectedItem().toString();
    }

    public void showRecognizedText(String recognizedText) {
        ((TextView) context.findViewById(R.id.speechResult)).setText(recognizedText);
    }

    public void setDate(String dateString) {
        date.setText(dateString, TextView.BufferType.NORMAL);
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
        DatePickerDialogWrapper_.builder().build().show(new DatePickerDialogWrapper.Callback() {
            @Override
            public void onDatePick(String date) {
                setDate(date);
            }
        }, context.getSupportFragmentManager());
    }

    @Click(R.id.endDate)
    void onShowEndDatePicker() {
        DatePickerDialogWrapper_.builder().build().show(new DatePickerDialogWrapper.Callback() {
            @Override
            public void onDatePick(String date) {
                setEndDate(date);
            }
        }, context.getSupportFragmentManager());
    }

    public void showSpeechStartButton() {
        speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_mic));
    }

    public void showSpeechStopButton() {
        speechButton.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_micoff));
    }

    public void setAccountingType(String accountingType) {
        for (int position = 0; position < accountingTypeSpinner.getAdapter().getCount(); position++) {
            String item = (String) accountingTypeSpinner.getAdapter().getItem(position);
            if(item.equals(accountingType)) {
                accountingTypeSpinner.setSelection(position);
            }
        }
    }

    public void hideEndDate() {
        disableEndDate();
        endDate.setVisibility(View.INVISIBLE);
        endDateLabel.setVisibility(View.INVISIBLE);
        endDateCheck.setVisibility(View.INVISIBLE);
    }

    public void enableEndDate() {
        endDate.setEnabled(true);
        endDateLabel.setEnabled(true);
    }

    public void disableEndDate() {
        endDate.setEnabled(false);
        endDateLabel.setEnabled(false);
    }

    public boolean isEndDateActive() {
        return endDateCheck.isChecked();
    }

    public void setEndDate(String dateString) {
        endDate.setText(dateString, TextView.BufferType.NORMAL);
    }

    public void showEndDate() {
        endDate.setVisibility(View.VISIBLE);
        endDateLabel.setVisibility(View.VISIBLE);
        endDateCheck.setVisibility(View.VISIBLE);
    }

    public void setAccountingInterval(String accountingInterval) {
        for (int position = 0; position < accountingIntervalSpinner.getAdapter().getCount(); position++) {
            String item = (String) accountingIntervalSpinner.getAdapter().getItem(position);
            if(item.equals(accountingInterval)) {
                accountingIntervalSpinner.setSelection(position);
            }
        }
    }

    public String getEndDate() {
        return endDate.getText().toString();
    }
}
