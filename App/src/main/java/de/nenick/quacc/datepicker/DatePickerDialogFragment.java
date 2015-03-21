package de.nenick.quacc.datepicker;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;

import java.util.Calendar;

@EFragment
public class DatePickerDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @Bean
    DatePickerFormatUtil datePickerFormatUtil;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,
                datePickerFormatUtil.createResultIntent(year, monthOfYear, dayOfMonth));
    }
}