package de.nenick.quacc.database.bookingintervalentry;

import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryColumns;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryContentValues;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryCursor;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntrySelection;

@EBean
public class BookingIntervalEntryRepository extends BaseRepository<BookingIntervalEntryContentValues, BookingIntervalEntrySpec, BookingIntervalEntryCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingIntervalEntryColumns.ALL_COLUMNS, BookingEntryColumns.ALL_COLUMNS, String.class), BookingIntervalColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingIntervalEntryContentValues values, BookingIntervalEntrySpec specification) {
        BookingIntervalEntrySelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingIntervalEntryCursor query(BookingIntervalEntrySpec specification) {
        BookingIntervalEntrySelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }

    @Override
    public Uri uri() {
        return BookingIntervalEntryColumns.CONTENT_URI;
    }
}
