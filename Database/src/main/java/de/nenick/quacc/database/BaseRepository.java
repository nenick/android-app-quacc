package de.nenick.quacc.database;

import android.app.Activity;
import android.app.LoaderManager;
import android.content.ContentUris;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.common.reflect.TypeToken;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

import java.lang.reflect.Array;

import de.nenick.quacc.database.provider.base.AbstractContentValues;
import de.nenick.quacc.database.provider.base.AbstractCursor;
import de.nenick.quacc.database.provider.base.AbstractSelection;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateCursor;

@EBean
public abstract class BaseRepository<CONTENT extends AbstractContentValues, SPEC extends QuerySpecification, CURSOR extends AbstractCursor> {

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

    /** instant query */
    public abstract CURSOR query(SPEC specification); /* {
        AbstractSelection selection = specification.toSelection();
        return selection.query(context.getContentResolver());
    } */

    public void loader(int id, final SPEC specification, final LoaderCallback<CURSOR> callback) {
        ((Activity)context).getLoaderManager().restartLoader(id, null, new LoaderManager.LoaderCallbacks<Cursor>() {
            @Override
            public Loader<Cursor> onCreateLoader(int id, Bundle args) {
                return new CursorLoader(context, specification.toSelection().uri(), specification.projection(), specification.toSelection().sel(), specification.toSelection().args(), specification.sortBy());
            }

            @Override
            public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
                try {
                    TypeToken typeToken = new TypeToken<CURSOR>(getClass()) {};
                    Object wrapper = ((Class) typeToken.getTypes().rawTypes().toArray()[2]).getConstructors()[0].newInstance(data);
                    //noinspection unchecked
                    callback.onLoadFinished((CURSOR) wrapper);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onLoaderReset(Loader<Cursor> loader) {
                callback.onLoadFinished(null);
            }
        });
    }

    protected  <T> T[] concatenate(T[] a, T[] b) {
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

    public abstract Uri uri();

    public Context getContext() {
        return context;
    }

    //protected abstract CURSOR wrap(Cursor cursor);
}
