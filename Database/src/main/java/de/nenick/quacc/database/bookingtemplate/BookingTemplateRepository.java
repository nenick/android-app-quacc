package de.nenick.quacc.database.bookingtemplate;

import android.database.Cursor;
import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class BookingTemplateRepository extends BaseRepository<BookingTemplateContentValues, BookingTemplateSpec, BookingTemplateCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingTemplateColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingTemplateContentValues values, BookingTemplateSpec specification) {
        BookingTemplateSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingTemplateCursor query(BookingTemplateSpec specification) {
        BookingTemplateSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }

    @Override
    public Uri uri() {
        return BookingTemplateColumns.CONTENT_URI;
    }
}
