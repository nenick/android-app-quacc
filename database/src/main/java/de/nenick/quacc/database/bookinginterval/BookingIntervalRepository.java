package de.nenick.quacc.database.bookinginterval;

import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalContentValues;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalCursor;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class BookingIntervalRepository extends BaseRepository<BookingIntervalContentValues, BookingIntervalSpec, BookingIntervalCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingIntervalColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingIntervalContentValues values, BookingIntervalSpec specification) {
        BookingIntervalSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingIntervalCursor query(BookingIntervalSpec specification) {
        BookingIntervalSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }

    @Override
    public Uri uri() {
        return BookingIntervalColumns.CONTENT_URI;
    }
}
