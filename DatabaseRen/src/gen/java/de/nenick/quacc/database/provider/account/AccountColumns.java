package de.nenick.quacc.database.provider.account;

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
 * An unit where user store money e.g. bank and wallet
 */
public class AccountColumns implements BaseColumns {
    public static final String TABLE_NAME = "account";
    public static final Uri CONTENT_URI = Uri.parse(BaseQuAccProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    /**
     * Primary key.
     */
    public static final String _ID = BaseColumns._ID;

    /**
     * The name of the account.
     */
    public static final String NAME = "account__name";

    /**
     * The base to calculate the current amount of money. Values are stored in cents.
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
