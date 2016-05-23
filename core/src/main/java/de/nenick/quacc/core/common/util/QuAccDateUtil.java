package de.nenick.quacc.core.common.util;

import android.support.annotation.NonNull;

import org.androidannotations.annotations.EBean;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * Unifies date time handling.
 */
@EBean(scope = EBean.Scope.Singleton)
public class QuAccDateUtil {

    private TodayProvider todayProvider = new TodayProvider();

    public static QuAccDateUtil getInstance() {
        return QuAccDateUtil_.getInstance_(null);
    }

    public static final DateTimeFormatter defaultPattern = DateTimeFormat.forPattern("dd.MM.yyyy");

    public static DateTime currentDateTime() {
        return new DateTime();
    }

    public static String currentDate() {
        return getInstance().today().toString(defaultPattern);
    }

    public static String currentYear() {
        return String.valueOf(new DateTime().year().get());
    }

    public static int currentMonth() {
        return new DateTime().monthOfYear().get();
    }

    public DateTime toDateTime(int day, int month, String year) {
        return toDateTime(day, month, Integer.parseInt(year));
    }

    public static DateTime toDateTime(int day, int month, int year) {
        return DateTime.parse(day + "." + month + "." + year, defaultPattern);
    }

    public static Date toDate(int day, int month, String year) {
        return toDate(day, month, Integer.parseInt(year));
    }

    public static Date toDate(int day, int month, int year) {
        return toDate(toString(day, month, year));
    }

    public static Date toDate(String dateString) {
        return DateTime.parse(dateString, defaultPattern).toDate();
    }

    public static String toString(int day, int month, int year) {
        return toString(DateTime.parse(day + "." + month + "." + year, defaultPattern));
    }

    public static String toString(DateTime date) {
        return date.toString(defaultPattern);
    }

    public static String toString(Date date) {
        return toString(new DateTime(date));
    }

    public static int monthAfterOf(int month) {
        DateTime dateTime = DateTime.parse("1." + month + ".2015", defaultPattern);
        return dateTime.plusMonths(1).getMonthOfYear();
    }

    public static int monthBeforeOf(int month) {
        DateTime dateTime = DateTime.parse("1." + month + ".2015", defaultPattern);
        return dateTime.minusMonths(1).getMonthOfYear();
    }

    public static boolean isGreaterEq(Date base, DateTime expectedToBeGreater) {
        int compareResult = new DateTime(base).compareTo(expectedToBeGreater);
        return compareResult == -1 || compareResult == 0;
    }

    /**
     * DateTime at time start of the 1. day of given month.
     */
    public DateTime firstDayOfMonth(DateTime givenDate) {
        return givenDate.withDayOfMonth(1).withTimeAtStartOfDay();
    }

    /**
     * DateTime at time end of the last day of given month.
     * <p/>
     * Last day may be 28, 29, 30 or 31 depending of given month and year.
     */
    public DateTime lastDayOfMonth(DateTime givenDate) {
        DateTime lastDay = givenDate.withDayOfMonth(givenDate.dayOfMonth().getMaximumValue());
        return lastDay.plusDays(1).withTimeAtStartOfDay().minusMillis(1);
    }

    @NonNull
    public DateTime today() {
        return todayProvider.get();
    }

    public static class TodayProvider {
        public DateTime get() {
            return DateTime.now();
        }
    }
}
