package de.nenick.quacc.database.provider.accounting;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.QuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;

/**
 * Columns for the {@code accounting} table.
 */
public class AccountingColumns implements BaseColumns {
    public static final String TABLE_NAME = "accounting";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ACCOUNT_ID = "account_id";

    public static final String CATEGORY_ID = "category_id";

    public static final String COMMENT = "comment";

    public static final String INTERVAL = "accounting__interval";

    public static final String DATE = "date";

    public static final String TYPE = "accounting__type";

    /**
     * Values are stored in 100 cent.
     */
    public static final String VALUE = "value";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ACCOUNT_ID,
            CATEGORY_ID,
            COMMENT,
            INTERVAL,
            DATE,
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
            if (c.equals(DATE) || c.contains("." + DATE)) return true;
            if (c.equals(TYPE) || c.contains("." + TYPE)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNT = TABLE_NAME + "__" + AccountColumns.TABLE_NAME;
    public static final String PREFIX_CATEGORY = TABLE_NAME + "__" + CategoryColumns.TABLE_NAME;
}
