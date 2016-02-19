package de.nenick.quacc.database.provider.bookingtemplatekeyword;

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
 * Assign key word for template
 */
public class BookingTemplateKeywordColumns implements BaseColumns {
    public static final String TABLE_NAME = "booking_template_keyword";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Word or phrase to match template.
     */
    public static final String TEXT = "text";

    /**
     * Referenced booking template.
     */
    public static final String BOOKING_TEMPLATE_ID = "booking_template_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TEXT,
            BOOKING_TEMPLATE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TEXT) || c.contains("." + TEXT)) return true;
            if (c.equals(BOOKING_TEMPLATE_ID) || c.contains("." + BOOKING_TEMPLATE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_BOOKING_TEMPLATE = TABLE_NAME + "__" + BookingTemplateColumns.TABLE_NAME;
}
