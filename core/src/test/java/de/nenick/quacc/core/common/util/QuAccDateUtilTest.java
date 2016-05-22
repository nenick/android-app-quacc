package de.nenick.quacc.core.common.util;

import org.joda.time.DateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QuAccDateUtilTest {

    public static final int MILLISECONDS_OF_ONE_DAY = 24 * 60 * 60 * 1000 - 1;

    private QuAccDateUtil quAccDateUtil = new QuAccDateUtil();
    /*{
        @NonNull
        @Override
        protected DateTime today() {
            DateTime dateTime = DateTime.parse("2016-12-24").withTime(13, 10, 50, 300);
            assertEquals(24, dateTime.getDayOfMonth());
            assertEquals(12, dateTime.getMonthOfYear());
            assertEquals(2016, dateTime.getYear());
            assertEquals(13, dateTime.getHourOfDay());
            assertEquals(10, dateTime.getMinuteOfHour());
            assertEquals(50, dateTime.getSecondOfMinute());
            assertEquals(300, dateTime.getMillisOfSecond());
            return dateTime;
        }
    };*/

    @Test
    public void testFirstDayOfCurrentMonth() {
        DateTime firstDayOfCurrentMonth = quAccDateUtil.firstDayOfMonth(DateTime.parse("2016-12-24").withTime(13, 10, 50, 300));

        assertEquals(1, firstDayOfCurrentMonth.getDayOfMonth());
        assertEquals(12, firstDayOfCurrentMonth.getMonthOfYear());
        assertEquals(2016, firstDayOfCurrentMonth.getYear());
        assertEquals(0, firstDayOfCurrentMonth.getMillisOfDay());
    }

    @Test
    public void testLastDayOfCurrentMonth() {
        DateTime firstDayOfCurrentMonth = quAccDateUtil.lastDayOfMonth(DateTime.parse("2016-12-24").withTime(13, 10, 50, 300));

        assertEquals(31, firstDayOfCurrentMonth.getDayOfMonth());
        assertEquals(12, firstDayOfCurrentMonth.getMonthOfYear());
        assertEquals(2016, firstDayOfCurrentMonth.getYear());
        assertEquals(MILLISECONDS_OF_ONE_DAY, firstDayOfCurrentMonth.getMillisOfDay());
    }
}