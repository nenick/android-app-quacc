package de.nenick.quacc.database.provider.interval;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.QuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingColumns;
import de.nenick.quacc.database.provider.intervalchange.IntervalChangeColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;

/**
 * Columns for the {@code interval} table.
 */
public class IntervalColumns implements BaseColumns {
    public static final String TABLE_NAME = "interval";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ACCOUNT_ID = "interval__account_id";

    public static final String CATEGORY_ID = "interval__category_id";

    public static final String COMMENT = "interval__comment";

    /**
     * The type of this interval how it will add accounting.
     */
    public static final String INTERVAL = "interval__interval";

    /**
     * Start date when the interval have the first accounting.
     */
    public static final String DATE_START = "date_start";

    /**
     * Last date where the interval is active. This must not match the last accounting date.
     */
    public static final String DATE_END = "date_end";

    /**
     * This is the last account entry created by this interval.
     */
    public static final String DATE_LAST = "date_last";

    /**
     * Until this date all accounting which belong to this interval are created or updated.
     */
    public static final String DATE_UPDATED_UNTIL = "date_updated_until";

    public static final String TYPE = "interval__type";

    /**
     * Values are stored in 100 cent.
     */
    public static final String VALUE = "interval__value";


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
            TYPE,
            VALUE
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
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNT = TABLE_NAME + "__" + AccountColumns.TABLE_NAME;
    public static final String PREFIX_CATEGORY = TABLE_NAME + "__" + CategoryColumns.TABLE_NAME;
}
