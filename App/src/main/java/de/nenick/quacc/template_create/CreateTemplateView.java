package de.nenick.quacc.template_create;

import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.melnykov.fab.FloatingActionButton;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.ViewById;

import de.nenick.quacc.R;
import de.nenick.quacc.mvp.BaseView;

@EBean
public class CreateTemplateView extends BaseView {

    @ViewById(R.id.account)
    Spinner accountSpinner;

    @ViewById(R.id.type)
    Spinner accountingTypeSpinner;

    @ViewById(R.id.interval)
    Spinner accountingIntervalSpinner;

    @ViewById(R.id.category)
    Spinner accountingCategorySpinner;

    @ViewById(R.id.value)
    EditText valueField;

    @ViewById(R.id.valueError)
    TextView valueError;

    @ViewById(R.id.comment)
    EditText comment;

    @ViewById(R.id.btn_speech_recognition)
    FloatingActionButton speechButton;

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

    public String getValue() {
        return valueField.getText().toString();
    }

    public String getComment() {
        return comment.getText().toString();
    }

    public void showValueParsingError(int resourceId) {
        valueError.setText(resourceId);
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

    public void setAccountingInterval(String value) {
        for (int position = 0; position < accountingIntervalSpinner.getAdapter().getCount(); position++) {
            String item = (String) accountingIntervalSpinner.getAdapter().getItem(position);
            if(item.equals(value)) {
                accountingIntervalSpinner.setSelection(position);
            }
        }
    }

    public void setAccountingCategory(String value) {
        for (int position = 0; position < accountingCategorySpinner.getAdapter().getCount(); position++) {
            String item = (String) accountingCategorySpinner.getAdapter().getItem(position);
            if(item.equals(value)) {
                accountingCategorySpinner.setSelection(position);
            }
        }
    }

    public void setComment(String text) {
        comment.setText(text);
    }

    public void setValue(String value) {
        valueField.setText(value);
    }
}
