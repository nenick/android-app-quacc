package de.nenick.quacc.database;

import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import de.nenick.quacc.database.provider.base.AbstractCursor;

public abstract class QuAccLoaderCallback<CURSOR extends AbstractCursor> implements LoaderManager.LoaderCallbacks<CURSOR> {

    @Override
    public void onLoadFinished(Loader<CURSOR> loader, CURSOR data) {
        onLoadFinished(data);
    }

    @Override
    public void onLoaderReset(Loader<CURSOR> loader) {
        onLoadFinished(null);
    }

    public abstract void onLoadFinished(CURSOR data);
}
