package de.nenick.quacc.database.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDatabaseDateUtil {

    /**
     * Convert the date string to a date object.
     *
     * @param dateString
     * @return date object
     */
    public static Date parse(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
