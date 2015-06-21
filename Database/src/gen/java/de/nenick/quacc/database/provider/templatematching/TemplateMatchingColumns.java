package de.nenick.quacc.database.provider.templatematching;

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
 * Columns for the {@code template_matching} table.
 */
public class TemplateMatchingColumns implements BaseColumns {
    public static final String TABLE_NAME = "template_matching";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    public static final String TEXT = "text";

    public static final String ACCOUNTING_TEMPLATE_ID = "accounting_template_id";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            TEXT,
            ACCOUNTING_TEMPLATE_ID
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(TEXT) || c.contains("." + TEXT)) return true;
            if (c.equals(ACCOUNTING_TEMPLATE_ID) || c.contains("." + ACCOUNTING_TEMPLATE_ID)) return true;
        }
        return false;
    }

    public static final String PREFIX_ACCOUNTING_TEMPLATE = TABLE_NAME + "__" + AccountingTemplateColumns.TABLE_NAME;
}
