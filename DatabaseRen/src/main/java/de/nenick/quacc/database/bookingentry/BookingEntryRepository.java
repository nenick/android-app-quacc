package de.nenick.quacc.database.bookingentry;

import android.database.Cursor;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import java.lang.reflect.Array;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class BookingEntryRepository extends BaseRepository<BookingEntryContentValues, BookingEntrySpec, BookingEntryCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingEntryColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingEntryContentValues values, BookingEntrySpec specification) {
        BookingEntrySelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingEntryCursor query(BookingEntrySpec specification) {
        BookingEntrySelection selection = specification.toSelection();
        String[] projection = concatenate(JOIN_BUG_WORKAROUND, specification.projection());
        return selection.query(context.getContentResolver(), projection, specification.sortBy());
    }

    @Override
    public Uri uri() {
        return BookingEntryColumns.CONTENT_URI;
    }
}
