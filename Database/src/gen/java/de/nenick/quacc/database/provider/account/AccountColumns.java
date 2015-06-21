package de.nenick.quacc.database.provider.account;

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
 * Consolidate the accounting.
 */
public class AccountColumns implements BaseColumns {
    public static final String TABLE_NAME = "account";
    public static final Uri CONTENT_URI = Uri.parse(QuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * The name of the account set by the user.
     */
    public static final String NAME = "account__name";

    /**
     * The base to calculate the current amount of money. Values are stored in 100 cent.
     */
    public static final String INITIALVALUE = "account__initialvalue";


    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] ALL_COLUMNS = new String[] {
            _ID,
            NAME,
            INITIALVALUE
    };
    // @formatter:on

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (c.equals(NAME) || c.contains("." + NAME)) return true;
            if (c.equals(INITIALVALUE) || c.contains("." + INITIALVALUE)) return true;
        }
        return false;
    }

}
