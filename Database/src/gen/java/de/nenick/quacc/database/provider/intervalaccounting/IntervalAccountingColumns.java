package de.nenick.quacc.database.provider.intervalaccounting;

import android.net.Uri;
import android.provider.BaseColumns;

import de.nenick.quacc.database.provider.QuAccProvider;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.accounting.AccountingColumns;
import de.nenick.quacc.database.provider.accountingtemplate.AccountingTemplateColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.database.provider.interval.IntervalColumns;
import de.nenick.quacc.database.provider.intervalaccounting.IntervalAccountingColumns;
import de.nenick.quacc.database.provider.templatematching.TemplateMatchingColumns;

/**
 * Columns for the {@code interval_accounting} table.
 */
public class IntervalAccountingColumns implements BaseColumns {
    public static final String TABLE_NAME = "interval_accounting";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String INTERVAL_ID = "interval_id";

    public static final String ACCOUNTING_ID = "accounting_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            INTERVAL_ID,
            ACCOUNTING_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(INTERVAL_ID) || c.contains("." + INTERVAL_ID)) return true;
            if (c.equals(ACCOUNTING_ID) || c.contains("." + ACCOUNTING_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_INTERVAL = TABLE_NAME + "__" + IntervalColumns.TABLE_NAME;
    public static final String PREFIX_ACCOUNTING = TABLE_NAME + "__" + AccountingColumns.TABLE_NAME;
}
