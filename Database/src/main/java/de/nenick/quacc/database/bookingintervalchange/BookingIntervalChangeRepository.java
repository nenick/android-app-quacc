package de.nenick.quacc.database.bookingintervalchange;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.base.Repository;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeColumns;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeContentValues;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeCursor;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeSelection;

@EBean
public class BookingIntervalChangeRepository extends Repository<BookingIntervalChangeContentValues, BookingIntervalChangeSpec, BookingIntervalChangeSelection, BookingIntervalChangeCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(BookingIntervalChangeColumns.ALL_COLUMNS, BookingIntervalColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingIntervalChangeContentValues values, BookingIntervalChangeSpec specification) {
        BookingIntervalChangeSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingIntervalChangeCursor query(QuerySpecification<BookingIntervalChangeSelection> specification) {
        BookingIntervalChangeSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }
}
