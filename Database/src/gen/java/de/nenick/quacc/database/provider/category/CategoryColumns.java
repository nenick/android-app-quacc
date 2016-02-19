package de.nenick.quacc.database.provider.category;

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
 * Group for booking entry with main and sub category
 */
public class CategoryColumns implements BaseColumns {
    public static final String TABLE_NAME = "category";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Name of the category.
     */
    public static final String NAME = "category__name";

    /**
     * Main group of the category.
     */
    public static final String SECTION = "category__section";

    /**
     * Possible booking interval for this category.
     */
    public static final String INTERVAL = "category__interval";

    /**
     * Possible booking direction for this category.
     */
    public static final String DIRECTION = "category__direction";

    /**
     * Support for sort/filter by main and sub categories. (0 = Main; 1 = Sub)
     */
    public static final String LEVEL = "category__level";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            SECTION,
            INTERVAL,
            DIRECTION,
            LEVEL
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(SECTION) || c.contains("." + SECTION)) return true;
            if (c.equals(INTERVAL) || c.contains("." + INTERVAL)) return true;
            if (c.equals(DIRECTION) || c.contains("." + DIRECTION)) return true;
            if (c.equals(LEVEL) || c.contains("." + LEVEL)) return true;
        }
        return false;
    }

}
