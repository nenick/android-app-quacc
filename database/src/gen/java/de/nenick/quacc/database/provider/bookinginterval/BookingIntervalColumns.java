package de.nenick.quacc.database.provider.bookinginterval;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.BaseQuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeColumns;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;

/**
 * Booking which will be repeated over time period.
 */
public class BookingIntervalColumns implements BaseColumns {
    public static final String TABLE_NAME = "booking_interval";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Referenced account.
     */
    public static final String ACCOUNT_ID = "booking_interval__account_id";

    /**
     * Referenced category.
     */
    public static final String CATEGORY_ID = "booking_interval__category_id";

    /**
     * Optional description for booking entries.
     */
    public static final String COMMENT = "booking_interval__comment";

    /**
     * Strategy how to repeat.
     */
    public static final String INTERVAL = "booking_interval__interval";

    /**
     * Date when the interval creates the first booking entry.
     */
    public static final String DATE_START = "date_start";

    /**
     * Date when the interval create no more booking entries. This must not match the last created booking date.
     */
    public static final String DATE_END = "date_end";

    /**
     * This is the last booking entry created by this interval.
     */
    public static final String DATE_LAST = "date_last";

    /**
     * Necessary booking entries are created or updated until this date.
     */
    public static final String DATE_UPDATED_UNTIL = "date_updated_until";

    /**
     * direction how booking entries are created.
     */
    public static final String DIRECTION = "booking_interval__direction";

    /**
     * Values are stored in cents.
     */
    public static final String AMOUNT = "booking_interval__amount";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ACCOUNT_ID,
            CATEGORY_ID,
            COMMENT,
            INTERVAL,
            DATE_START,
            DATE_END,
            DATE_LAST,
            DATE_UPDATED_UNTIL,
            DIRECTION,
            AMOUNT
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ACCOUNT_ID) || c.contains("." + ACCOUNT_ID)) return true;
            if (c.equals(CATEGORY_ID) || c.contains("." + CATEGORY_ID)) return true;
            if (c.equals(COMMENT) || c.contains("." + COMMENT)) return true;
            if (c.equals(INTERVAL) || c.contains("." + INTERVAL)) return true;
            if (c.equals(DATE_START) || c.contains("." + DATE_START)) return true;
            if (c.equals(DATE_END) || c.contains("." + DATE_END)) return true;
            if (c.equals(DATE_LAST) || c.contains("." + DATE_LAST)) return true;
            if (c.equals(DATE_UPDATED_UNTIL) || c.contains("." + DATE_UPDATED_UNTIL)) return true;
            if (c.equals(DIRECTION) || c.contains("." + DIRECTION)) return true;
            if (c.equals(AMOUNT) || c.contains("." + AMOUNT)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNT = TABLE_NAME + "__" + AccountColumns.TABLE_NAME;
    public static final String PREFIX_CATEGORY = TABLE_NAME + "__" + CategoryColumns.TABLE_NAME;
}
