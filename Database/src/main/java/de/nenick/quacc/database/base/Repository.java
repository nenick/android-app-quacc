package de.nenick.quacc.database.base;

import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import de.nenick.quacc.database.QuerySpecification;
import de.nenick.quacc.database.provider.base.AbstractContentValues;
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.base.AbstractSelection;

@EBean
public abstract class Repository<CONTENT extends AbstractContentValues, SPEC extends QuerySpecification, SELECTION extends AbstractSelection, CURSOR extends AbstractCursor> {

    @RootContext
    protected Context context;

    public long insert(CONTENT values) {
        if (values.values().size() == 0) {
            // INFO looks like a bug in AndroidContentProviderGenerator where zero values leads to invalid sq statement
            throw new IllegalArgumentException("Provide minimal one column value, it may be null. Creating entries without content is not supported duo sqlite bug.");
        }
        Uri insert = values.insert(context.getContentResolver());
        return ContentUris.parseId(insert);
    }

    public void delete(SPEC specification) {
        AbstractSelection selection = specification.toSelection();
        selection.delete(context.getContentResolver());
    }

    // INFO extend AndroidContentProviderGenerator with generic repository methods, AbstractSelection miss methods update() and query()

    public abstract void update(CONTENT values, SPEC specification); /* {
        AbstractSelection selection = specification.toSelection();
        values.update(context.getContentResolver(), selection);
    } */

    public abstract CURSOR query(QuerySpecification<SELECTION> specification); /* {
        AbstractSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver());
    } */
}
