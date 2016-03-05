package de.nenick.quacc.database.provider.bookingentry;

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
 * Booking entry for specific account.
 */
public class BookingEntryColumns implements BaseColumns {
    public static final String TABLE_NAME = "booking_entry";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Referenced account.
     */
    public static final String ACCOUNT_ID = "booking_entry__account_id";

    /**
     * Referenced category.
     */
    public static final String CATEGORY_ID = "booking_entry__category_id";

    /**
     * Optional description for this entry.
     */
    public static final String COMMENT = "booking_entry__comment";

    /**
     * Entry was created by this interval strategy.
     */
    public static final String INTERVAL = "booking_entry__interval";

    /**
     * Booking is done at this date. May be past or future.
     */
    public static final String DATE = "date";

    /**
     * Defines how this booking entry will effect the account money.
     */
    public static final String DIRECTION = "booking_entry__direction";

    /**
     * Values are stored in cents.
     */
    public static final String AMOUNT = "booking_entry__amount";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ACCOUNT_ID,
            CATEGORY_ID,
            COMMENT,
            INTERVAL,
            DATE,
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
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(DIRECTION) || c.contains("." + DIRECTION)) return true;
            if (c.equals(AMOUNT) || c.contains("." + AMOUNT)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNT = TABLE_NAME + "__" + AccountColumns.TABLE_NAME;
    public static final String PREFIX_CATEGORY = TABLE_NAME + "__" + CategoryColumns.TABLE_NAME;
}
