package de.nenick.quacc.database;

import android.support.annotation.Nullable;

import de.nenick.quacc.database.provider.base.AbstractSelection;

public abstract class QuerySpecification<T extends AbstractSelection> {

    public abstract T toSelection();

    @Nullable
    public String sortBy() {
        return null;
    }

    @Nullable
    public String[] projection() {
        return null;
    }
}
