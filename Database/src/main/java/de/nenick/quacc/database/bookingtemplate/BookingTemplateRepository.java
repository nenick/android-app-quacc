package de.nenick.quacc.database.bookingtemplate;

import com.google.common.collect.ObjectArrays;

import org.androidannotations.annotations.EBean;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.base.Repository;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateContentValues;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateSelection;
import de.nenick.quacc.database.provider.category.CategoryColumns;

@EBean
public class BookingTemplateRepository extends Repository<BookingTemplateContentValues, BookingTemplateSpec, BookingTemplateSelection, BookingTemplateCursor> {

    private final String[] JOIN_BUG_WORKAROUND = ObjectArrays.concat(ObjectArrays.concat(BookingTemplateColumns.ALL_COLUMNS, AccountColumns.ALL_COLUMNS, String.class), CategoryColumns.ALL_COLUMNS, String.class);

    @Override
    public void update(BookingTemplateContentValues values, BookingTemplateSpec specification) {
        BookingTemplateSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    }

    @Override
    public BookingTemplateCursor query(QuerySpecification<BookingTemplateSelection> specification) {
        BookingTemplateSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver(), JOIN_BUG_WORKAROUND);
    }
}
