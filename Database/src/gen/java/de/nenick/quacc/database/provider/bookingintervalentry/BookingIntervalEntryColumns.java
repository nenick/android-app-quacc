package de.nenick.quacc.database.provider.bookingintervalentry;

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
 * Reference to concrete booking entry for an interval.
 */
public class BookingIntervalEntryColumns implements BaseColumns {
    public static final String TABLE_NAME = "booking_interval_entry";
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
     * Referenced booking entry.
     */
    public static final String BOOKING_ENTRY_ID = "booking_entry_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            BOOKING_INTERVAL_ID,
            BOOKING_ENTRY_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(BOOKING_INTERVAL_ID) || c.contains("." + BOOKING_INTERVAL_ID)) return true;
            if (c.equals(BOOKING_ENTRY_ID) || c.contains("." + BOOKING_ENTRY_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_BOOKING_INTERVAL = TABLE_NAME + "__" + BookingIntervalColumns.TABLE_NAME;
    public static final String PREFIX_BOOKING_ENTRY = TABLE_NAME + "__" + BookingEntryColumns.TABLE_NAME;
}
