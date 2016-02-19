package de.nenick.quacc.database.bookingtemplatekeyword;

import android.net.Uri;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.BaseRepository;
import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordContentValues;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordCursor;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordSelection;

@EBean
public class BookingTemplateKeywordRepository extends BaseRepository<BookingTemplateKeywordContentValues, BookingTemplateKeywordSpec, BookingTemplateKeywordCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(BookingTemplateKeywordColumns.ALL_COLUMNS, BookingTemplateColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingTemplateKeywordContentValues values, BookingTemplateKeywordSpec specification) {
        BookingTemplateKeywordSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingTemplateKeywordCursor query(BookingTemplateKeywordSpec specification) {
        BookingTemplateKeywordSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }

    @Override
    public Uri uri() {
        return BookingTemplateKeywordColumns.CONTENT_URI;
    }
}
