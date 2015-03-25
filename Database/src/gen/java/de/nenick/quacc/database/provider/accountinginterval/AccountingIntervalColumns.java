package de.nenick.quacc.database.provider.accountinginterval;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.QuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryColumns;
import de.nenick.quacc.database.provider.accountinginterval.AccountingIntervalColumns;

/**
 * Available intervals for an accounting
 */
public class AccountingIntervalColumns implements BaseColumns {
    public static final String TABLE_NAME = "accounting_interval";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * Name
     */
    public static final String NAME = "accounting_interval__name";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
        }
        return false;
    }

}
