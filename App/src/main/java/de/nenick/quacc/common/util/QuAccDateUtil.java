package de.nenick.quacc.common.util;

import org.androidannotations.annotations.EBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@EBean
public class QuAccDateUtil {

    public static final int DATE_FORMAT = SimpleDateFormat.MEDIUM;
    public static final Locale DATE_LOCAL = Locale.GERMAN;

    public static String toString(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        DateFormat df = getDefaultDateFormat();
        return df.format(calendar.getTime());
    }

    public static Date asDate(int year, int month, int day) {
        return parse(toString(year, month, day));
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
            DateFormat df = QuAccDateUtil.getDefaultDateFormat();
            return df.parse(dateString);
        } catch (ParseException e) {
            // This should never occur in a released version.
            throw new IllegalStateException("Unknown date format, expected is dd.mm.jjjj");
        }
    }

    public static String currentYear() {
        Calendar c = Calendar.getInstance(DATE_LOCAL);
        int year = c.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    public static String currentMonth() {
        Calendar c = Calendar.getInstance(DATE_LOCAL);
        int month = c.get(Calendar.MONTH);
        switch (month) {
            case 0:
                return "Januar";
            case 1:
                return "Februar";
            case 2:
                return "März";
            case 3:
                return "April";
            case 4:
                return "Mai";
            case 5:
                return "Juni";
            case 6:
                return "Juli";
            case 7:
                return "August";
            case 8:
                return "September";
            case 9:
                return "Oktober";
            case 10:
                return "November";
            case 11:
                return "Dezember";
        }
        throw new IllegalStateException();
    }

    public static Date asDate(int day, String month, String year) {
        return asDate(Integer.parseInt(year), getMonthIndex(month), day);
    }

    private static int getMonthIndex(String name) {
        if("Januar".equals(name)) {
            return 0;
        } else if ("Februar".equals(name)) {
            return 1;
        } else if ("März".equals(name)) {
            return 2;
        } else if ("April".equals(name)) {
            return 3;
        } else if ("Mai".equals(name)) {
            return 4;
        } else if ("Juni".equals(name)) {
            return 5;
        } else if ("Juli".equals(name)) {
            return 6;
        } else if ("August".equals(name)) {
            return 7;
        } else if ("September".equals(name)) {
            return 8;
        } else if ("Oktober".equals(name)) {
            return 9;
        } else if ("November".equals(name)) {
            return 10;
        } else if ("Dezember".equals(name)) {
            return 11;
        } else {
            throw new IllegalStateException();
        }
    }
}
