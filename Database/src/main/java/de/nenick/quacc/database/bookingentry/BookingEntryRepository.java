package de.nenick.quacc.database.bookingentry;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import java.lang.reflect.Array;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.base.Repository;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryContentValues;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryCursor;
import de.nenick.quacc.database.provider.bookingentry.BookingEntrySelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class BookingEntryRepository extends Repository<BookingEntryContentValues, BookingEntrySpec, BookingEntrySelection, BookingEntryCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingEntryColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingEntryContentValues values, BookingEntrySpec specification) {
        BookingEntrySelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingEntryCursor query(QuerySpecification<BookingEntrySelection> specification) {
        BookingEntrySelection selection = specification.toSelection();
        String[] projection = concatenate(JOIN_BUG_WORKAROUND, specification.projection());
        return selection.query(context.getContentResolver(), projection, specification.sortBy());
    }

    private <T> T[] concatenate(T[] a, T[] b) {
        if (b == null) {
            return a;
        }

        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
}
