package de.nenick.quacc.datepicker;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.widget.DatePicker;

import org.androidannotations.annotations.EFragment;

import java.util.Calendar;

import de.nenick.quacc.common.util.QuAccDateUtil;

@EFragment
public class DatePickerDialogWrapper extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private static final String TAG = "Date Picker";

    public interface Callback {
        void onDatePick(String date);
    }

    private Callback callback;

    public void show(Callback callback, FragmentManager fragmentManager) {
        this.callback = callback;
        show(fragmentManager, TAG);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        callback.onDatePick(QuAccDateUtil.toString(dayOfMonth, monthOfYear, year));
    }

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
}