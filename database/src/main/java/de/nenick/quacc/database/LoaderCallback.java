package de.nenick.quacc.database;

import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractCursor;

public interface LoaderCallback<CURSOR extends AbstractCursor> {

    void onLoadFinished(@Nullable CURSOR cursor);
}
