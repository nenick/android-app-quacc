package de.nenick.quacc;

import java.util.Calendar;
import java.util.Locale;

public class TestDateUtil {
    static Calendar calendar = Calendar.getInstance(Locale.GERMAN);

    public static String year() {
        return String.valueOf(calendar.get(Calendar.YEAR));
    }

    public static String year(int value) {
        return String.valueOf(value);
    }

    public static String month() {
        int value = calendar.get(Calendar.MONTH) + 1;
        return withLeadingZero(value);
    }

    public static String month(int value) {
        return withLeadingZero(value);
    }

    public static String day() {
        int value = calendar.get(Calendar.DAY_OF_MONTH);
        return withLeadingZero(value);
    }

    public static String day(int value) {
        return withLeadingZero(value);
    }

    public static String withLeadingZero(int value) {
        String asString = String.valueOf(value);
        if (value >= 10) {
            return asString;
        }
        return "0" + asString;
    }

    /**
     * Create the expected string representation.
     *
     * @param day values 1 - 31
     * @param month values 1 - 12
     * @param year values 1000 - 9999
     *
     * @return date as string
     */
    public static String date(int day, int month, int year) {
        return day + "." + month + "." + year;
    }
}
