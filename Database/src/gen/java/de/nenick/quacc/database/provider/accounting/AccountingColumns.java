package de.nenick.quacc.database.provider.accounting;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.QuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingcategory.AccountingCategoryColumns;

/**
 * Accounting
 */
public class AccountingColumns implements BaseColumns {
    public static final String TABLE_NAME = "accounting";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String ACCOUNT_ID = "account_id";

    /**
     * Short description of the accounting.
     */
    public static final String NAME = "accounting__name";

    /**
     * Extra description of the accounting.
     */
    public static final String DESCRIPTION = "accounting__description";

    public static final String ACCOUNTING_INTERVAL = "accounting_interval";

    public static final String ACCOUNTING_CATEGORY_ID = "accounting_category_id";

    public static final String ACCOUNTING_DATE = "accounting_date";

    public static final String ACCOUNTING_TYPE = "accounting_type";

    /**
     * Values are stored with two decimals (1 Euro = 100)
     */
    public static final String VALUE = "value";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            ACCOUNT_ID,
            NAME,
            DESCRIPTION,
            ACCOUNTING_INTERVAL,
            ACCOUNTING_CATEGORY_ID,
            ACCOUNTING_DATE,
            ACCOUNTING_TYPE,
            VALUE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(ACCOUNT_ID) || c.contains("." + ACCOUNT_ID)) return true;
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(DESCRIPTION) || c.contains("." + DESCRIPTION)) return true;
            if (c.equals(ACCOUNTING_INTERVAL) || c.contains("." + ACCOUNTING_INTERVAL)) return true;
            if (c.equals(ACCOUNTING_CATEGORY_ID) || c.contains("." + ACCOUNTING_CATEGORY_ID)) return true;
            if (c.equals(ACCOUNTING_DATE) || c.contains("." + ACCOUNTING_DATE)) return true;
            if (c.equals(ACCOUNTING_TYPE) || c.contains("." + ACCOUNTING_TYPE)) return true;
            if (c.equals(VALUE) || c.contains("." + VALUE)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNT = TABLE_NAME + "__" + AccountColumns.TABLE_NAME;
    public static final String PREFIX_ACCOUNTING_CATEGORY = TABLE_NAME + "__" + AccountingCategoryColumns.TABLE_NAME;
}
