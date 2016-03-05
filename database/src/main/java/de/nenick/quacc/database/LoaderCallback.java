package de.nenick.quacc.database;

import de.nenick.quacc.database.provider.base.AbstractCursor;

public interface LoaderCallback<CURSOR extends AbstractCursor> {

    void onLoadFinished(CURSOR cursor);
}
