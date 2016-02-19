package de.nenick.quacc.database;

import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;

import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.base.AbstractSelection;

public abstract class QuAccCursorLoader<CURSOR extends AbstractCursor> extends CursorLoader {

    public QuAccCursorLoader(Context context, QuerySpecification<? extends AbstractSelection> specification) {
        super(context, specification.toSelection().uri(), specification.projection(), specification.toSelection().sel(), specification.toSelection().args(), specification.sortBy());
    }

    @Override
    public CURSOR loadInBackground() {
        return wrap(super.loadInBackground());
    }

    protected abstract CURSOR wrap(Cursor cursor);
}
