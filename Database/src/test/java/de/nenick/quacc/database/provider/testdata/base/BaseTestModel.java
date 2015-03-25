package de.nenick.quacc.database.provider.testdata.base;

import android.content.ContentResolver;
import android.net.Uri;

import de.nenick.quacc.database.provider.QuAccProvider;

public class BaseTestModel implements DataModel {
    private final Class<?> mKlass;

    public BaseTestModel(Class<?> klass) {
        mKlass = klass;
    }

    private static Uri buildUriFor(Class<?> klass) {
        return new Uri.Builder()
                .scheme(ContentResolver.SCHEME_CONTENT)
                .authority(QuAccProvider.AUTHORITY)
                .appendPath(klass.getSimpleName().toLowerCase())
                .build();
    }

    @Override
    public Uri getUri() {
        return buildUriFor(mKlass);
    }

    @Override
    public Class<?> getModelClass() {
        return mKlass;
    }
}