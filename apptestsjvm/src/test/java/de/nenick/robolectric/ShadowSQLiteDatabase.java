package de.nenick.quacc.robolectric;

import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;

import org.robolectric.annotation.Implements;
import org.robolectric.internal.Shadow;
import org.robolectric.util.ReflectionHelpers;

/**
 * Force robolectric to use an in memory database.
 *
 * This leads to faster testing but will also hide the speed problem when you don't use
 * a transaction for batch database inserts. Without real database you can't check for the database file.
 */
@Implements(SQLiteDatabase.class)
public class ShadowSQLiteDatabase {

   /* public static SQLiteDatabase openDatabase(String path, SQLiteDatabase.CursorFactory factory, int flags, DatabaseErrorHandler errorHandler) {
        return Shadow.directlyOn(SQLiteDatabase.class, "openDatabase",
                new ReflectionHelpers.ClassParameter<>(String.class, ":memory:"),
                new ReflectionHelpers.ClassParameter<>(SQLiteDatabase.CursorFactory.class, factory),
                new ReflectionHelpers.ClassParameter<>(int.class, flags),
                new ReflectionHelpers.ClassParameter<>(DatabaseErrorHandler.class, errorHandler));
    }*/
}
