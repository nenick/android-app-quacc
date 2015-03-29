package de.nenick.quacc.datepicker;

import android.content.Intent;

import org.androidannotations.annotations.EBean;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

@EBean
public class DatePickerFormatUtil {

    public static final String EXTRA_YEAR = "year";
    public static final String EXTRA_MONTH = "month";
    public static final String EXTRA_DAY = "day";

    public static final int DATE_FORMAT = SimpleDateFormat.MEDIUM;
    public static final Locale DATE_LOCAL = Locale.GERMAN;

    public Intent createResultIntent(int year, int month, int day) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_YEAR, year);
        intent.putExtra(EXTRA_MONTH, month);
        intent.putExtra(EXTRA_DAY, day);
        return intent;
    }

    public String fromResultIntent(Intent data) {
        int year = data.getIntExtra(EXTRA_YEAR, 0);
        int month = data.getIntExtra(EXTRA_MONTH, 0);
        int day = data.getIntExtra(EXTRA_DAY, 0);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = getDefaultDateFormat();
        return df.format(calendar.getTime());
    }

    public String currentDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = getDefaultDateFormat();
        return df.format(calendar.getTime());
    }

    public static DateFormat getDefaultDateFormat() {
        return SimpleDateFormat.getDateInstance(DATE_FORMAT, DATE_LOCAL);
    }
}
