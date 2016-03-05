package de.nenick.quacc.database.provider.bookingintervalchange;

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
 * History booking interval change.
 */
public class BookingIntervalChangeColumns implements BaseColumns {
    public static final String TABLE_NAME = "booking_interval_change";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Referenced booking interval.
     */
    public static final String BOOKING_INTERVAL_ID = "booking_interval_id";

    /**
     * Mark if the change applies for all following up (true) or once for a single (false) booking entries.
     */
    public static final String FOLLOW_UP = "follow_up";

    /**
     * Date for the single entry change or for all entries since this date.
     */
    public static final String DATE = "date";

    /**
     * Optional description for the single entry change or for all entries since this date.
     */
    public static final String COMMENT = "booking_interval_change__comment";

    /**
     * Values are stored in positive cents.
     */
    public static final String AMOUNT = "booking_interval_change__amount";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            BOOKING_INTERVAL_ID,
            FOLLOW_UP,
            DATE,
            COMMENT,
            AMOUNT
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(BOOKING_INTERVAL_ID) || c.contains("." + BOOKING_INTERVAL_ID)) return true;
            if (c.equals(FOLLOW_UP) || c.contains("." + FOLLOW_UP)) return true;
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(COMMENT) || c.contains("." + COMMENT)) return true;
            if (c.equals(AMOUNT) || c.contains("." + AMOUNT)) return true;
        }
        return false;
    }

    public static final String PREFIX_BOOKING_INTERVAL = TABLE_NAME + "__" + BookingIntervalColumns.TABLE_NAME;
}
