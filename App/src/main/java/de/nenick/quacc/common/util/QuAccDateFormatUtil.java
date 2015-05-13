package de.nenick.quacc.common.util;

import org.androidannotations.annotations.EBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@EBean
public class QuAccDateFormatUtil {

    public static final int DATE_FORMAT = SimpleDateFormat.MEDIUM;
    public static final Locale DATE_LOCAL = Locale.GERMAN;

    public static String toString(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = getDefaultDateFormat();
        return df.format(calendar.getTime());
    }

    public static String currentDate() {
        Calendar calendar = Calendar.getInstance();
        DateFormat df = getDefaultDateFormat();
        return df.format(calendar.getTime());
    }

    public static DateFormat getDefaultDateFormat() {
        return SimpleDateFormat.getDateInstance(DATE_FORMAT, DATE_LOCAL);
    }

    public static Date parse(String dateString) {
        try {
            DateFormat df = QuAccDateFormatUtil.getDefaultDateFormat();
            return df.parse(dateString);
        } catch (ParseException e) {
            // This should never occur in a released version.
            throw new IllegalStateException("Unknown date format, expected is dd.mm.jjjj");
        }
    }
}
