package de.nenick.quacc.database.provider;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.DefaultDatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import de.nenick.quacc.database.BuildConfig;
import de.nenick.quacc.database.provider.account.AccountColumns;
import de.nenick.quacc.database.provider.bookingentry.BookingEntryColumns;
import de.nenick.quacc.database.provider.bookinginterval.BookingIntervalColumns;
import de.nenick.quacc.database.provider.bookingintervalchange.BookingIntervalChangeColumns;
import de.nenick.quacc.database.provider.bookingintervalentry.BookingIntervalEntryColumns;
import de.nenick.quacc.database.provider.bookingtemplate.BookingTemplateColumns;
import de.nenick.quacc.database.provider.bookingtemplatekeyword.BookingTemplateKeywordColumns;
import de.nenick.quacc.database.provider.category.CategoryColumns;

public class QuAccSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String TAG = QuAccSQLiteOpenHelper.class.getSimpleName();

    public static final String DATABASE_FILE_NAME = "quacc.db";
    private static final int DATABASE_VERSION = 1;
    private static QuAccSQLiteOpenHelper sInstance;
    private final Context mContext;
    private final QuAccSQLiteOpenHelperCallbacks mOpenHelperCallbacks;

    // @formatter:off
    public static final String SQL_CREATE_TABLE_ACCOUNT = "CREATE TABLE IF NOT EXISTS "
            + AccountColumns.TABLE_NAME + " ( "
            + AccountColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + AccountColumns.NAME + " TEXT NOT NULL, "
            + AccountColumns.INITIALVALUE + " INTEGER NOT NULL "
            + ", CONSTRAINT name_unique UNIQUE (account__name) ON CONFLICT FAIL"
            + ", CONSTRAINT name_empty CHECK(account__name <> '') ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_ENTRY = "CREATE TABLE IF NOT EXISTS "
            + BookingEntryColumns.TABLE_NAME + " ( "
            + BookingEntryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingEntryColumns.ACCOUNT_ID + " INTEGER NOT NULL, "
            + BookingEntryColumns.CATEGORY_ID + " INTEGER NOT NULL, "
            + BookingEntryColumns.COMMENT + " TEXT, "
            + BookingEntryColumns.INTERVAL + " TEXT NOT NULL, "
            + BookingEntryColumns.DATE + " INTEGER NOT NULL, "
            + BookingEntryColumns.DIRECTION + " TEXT NOT NULL, "
            + BookingEntryColumns.AMOUNT + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_account_id FOREIGN KEY (" + BookingEntryColumns.ACCOUNT_ID + ") REFERENCES account (_id) ON DELETE CASCADE"
            + ", CONSTRAINT fk_category_id FOREIGN KEY (" + BookingEntryColumns.CATEGORY_ID + ") REFERENCES category (_id) ON DELETE CASCADE"
            + ", CONSTRAINT direction_empty CHECK(booking_entry__direction <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT interval_empty CHECK(booking_entry__interval <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT amount_positive CHECK(booking_entry__amount > 0) ON CONFLICT FAIL"
            + ", CONSTRAINT date_positive CHECK(date > 0) ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_INTERVAL = "CREATE TABLE IF NOT EXISTS "
            + BookingIntervalColumns.TABLE_NAME + " ( "
            + BookingIntervalColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingIntervalColumns.ACCOUNT_ID + " INTEGER NOT NULL, "
            + BookingIntervalColumns.CATEGORY_ID + " INTEGER NOT NULL, "
            + BookingIntervalColumns.COMMENT + " TEXT, "
            + BookingIntervalColumns.INTERVAL + " TEXT NOT NULL, "
            + BookingIntervalColumns.DATE_START + " INTEGER NOT NULL, "
            + BookingIntervalColumns.DATE_END + " INTEGER NOT NULL, "
            + BookingIntervalColumns.DATE_LAST + " INTEGER NOT NULL, "
            + BookingIntervalColumns.DATE_UPDATED_UNTIL + " INTEGER NOT NULL, "
            + BookingIntervalColumns.DIRECTION + " TEXT NOT NULL, "
            + BookingIntervalColumns.AMOUNT + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_account_id FOREIGN KEY (" + BookingIntervalColumns.ACCOUNT_ID + ") REFERENCES account (_id) ON DELETE CASCADE"
            + ", CONSTRAINT fk_category_id FOREIGN KEY (" + BookingIntervalColumns.CATEGORY_ID + ") REFERENCES category (_id) ON DELETE CASCADE"
            + ", CONSTRAINT direction_empty CHECK(booking_interval__direction <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT interval_empty CHECK(booking_interval__interval <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT amount_zero CHECK(booking_interval__amount > 0) ON CONFLICT FAIL"
            + ", CONSTRAINT date_start_zero CHECK(date_start > 0) ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_INTERVAL_CHANGE = "CREATE TABLE IF NOT EXISTS "
            + BookingIntervalChangeColumns.TABLE_NAME + " ( "
            + BookingIntervalChangeColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingIntervalChangeColumns.BOOKING_INTERVAL_ID + " INTEGER NOT NULL, "
            + BookingIntervalChangeColumns.FOLLOW_UP + " INTEGER NOT NULL, "
            + BookingIntervalChangeColumns.DATE + " INTEGER NOT NULL, "
            + BookingIntervalChangeColumns.COMMENT + " TEXT, "
            + BookingIntervalChangeColumns.AMOUNT + " INTEGER "
            + ", CONSTRAINT fk_booking_interval_id FOREIGN KEY (" + BookingIntervalChangeColumns.BOOKING_INTERVAL_ID + ") REFERENCES booking_interval (_id) ON DELETE CASCADE"
            + ", CONSTRAINT date_positive CHECK(date > 0) ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_INTERVAL_ENTRY = "CREATE TABLE IF NOT EXISTS "
            + BookingIntervalEntryColumns.TABLE_NAME + " ( "
            + BookingIntervalEntryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingIntervalEntryColumns.BOOKING_INTERVAL_ID + " INTEGER NOT NULL, "
            + BookingIntervalEntryColumns.BOOKING_ENTRY_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_booking_interval_id FOREIGN KEY (" + BookingIntervalEntryColumns.BOOKING_INTERVAL_ID + ") REFERENCES booking_interval (_id) ON DELETE CASCADE"
            + ", CONSTRAINT fk_booking_entry_id FOREIGN KEY (" + BookingIntervalEntryColumns.BOOKING_ENTRY_ID + ") REFERENCES booking_entry (_id) ON DELETE CASCADE"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_TEMPLATE = "CREATE TABLE IF NOT EXISTS "
            + BookingTemplateColumns.TABLE_NAME + " ( "
            + BookingTemplateColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingTemplateColumns.ACCOUNT_ID + " INTEGER NOT NULL, "
            + BookingTemplateColumns.CATEGORY_ID + " INTEGER NOT NULL, "
            + BookingTemplateColumns.COMMENT + " TEXT, "
            + BookingTemplateColumns.INTERVAL + " TEXT NOT NULL, "
            + BookingTemplateColumns.DIRECTION + " TEXT NOT NULL "
            + ", CONSTRAINT fk_account_id FOREIGN KEY (" + BookingTemplateColumns.ACCOUNT_ID + ") REFERENCES account (_id) ON DELETE CASCADE"
            + ", CONSTRAINT fk_category_id FOREIGN KEY (" + BookingTemplateColumns.CATEGORY_ID + ") REFERENCES category (_id) ON DELETE CASCADE"
            + ", CONSTRAINT direction_empty CHECK(booking_template__direction <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT interval_empty CHECK(booking_template__interval <> '') ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_BOOKING_TEMPLATE_KEYWORD = "CREATE TABLE IF NOT EXISTS "
            + BookingTemplateKeywordColumns.TABLE_NAME + " ( "
            + BookingTemplateKeywordColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + BookingTemplateKeywordColumns.TEXT + " TEXT NOT NULL, "
            + BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID + " INTEGER NOT NULL "
            + ", CONSTRAINT fk_booking_template_id FOREIGN KEY (" + BookingTemplateKeywordColumns.BOOKING_TEMPLATE_ID + ") REFERENCES booking_template (_id) ON DELETE CASCADE"
            + ", CONSTRAINT text_empty CHECK(text <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT text_unique UNIQUE(text) ON CONFLICT FAIL"
            + " );";

    public static final String SQL_CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS "
            + CategoryColumns.TABLE_NAME + " ( "
            + CategoryColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CategoryColumns.NAME + " TEXT NOT NULL, "
            + CategoryColumns.SECTION + " TEXT NOT NULL, "
            + CategoryColumns.INTERVAL + " TEXT NOT NULL, "
            + CategoryColumns.DIRECTION + " TEXT NOT NULL, "
            + CategoryColumns.LEVEL + " INTEGER NOT NULL "
            + ", CONSTRAINT name_empty CHECK(category__name <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT interval_empty CHECK(category__interval <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT section_empty CHECK(category__section <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT direction_empty CHECK(category__direction <> '') ON CONFLICT FAIL"
            + ", CONSTRAINT section_and_name_unique UNIQUE (category__name, category__section) ON CONFLICT FAIL"
            + ", CONSTRAINT level_min CHECK (category__level >= 0) ON CONFLICT FAIL"
            + ", CONSTRAINT level_max CHECK (category__level <= 1) ON CONFLICT FAIL"
            + " );";

    // @formatter:on

    public static QuAccSQLiteOpenHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (sInstance == null) {
            sInstance = newInstance(context.getApplicationContext());
        }
        return sInstance;
    }

    private static QuAccSQLiteOpenHelper newInstance(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return newInstancePreHoneycomb(context);
        }
        return newInstancePostHoneycomb(context);
    }


    /*
     * Pre Honeycomb.
     */
    private static QuAccSQLiteOpenHelper newInstancePreHoneycomb(Context context) {
        return new QuAccSQLiteOpenHelper(context);
    }

    private QuAccSQLiteOpenHelper(Context context) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION);
        mContext = context;
        mOpenHelperCallbacks = new QuAccSQLiteOpenHelperCallbacks();
    }


    /*
     * Post Honeycomb.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static QuAccSQLiteOpenHelper newInstancePostHoneycomb(Context context) {
        return new QuAccSQLiteOpenHelper(context, new DefaultDatabaseErrorHandler());
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private QuAccSQLiteOpenHelper(Context context, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_FILE_NAME, null, DATABASE_VERSION, errorHandler);
        mContext = context;
        mOpenHelperCallbacks = new QuAccSQLiteOpenHelperCallbacks();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        if (BuildConfig.DEBUG) Log.d(TAG, "onCreate");
        mOpenHelperCallbacks.onPreCreate(mContext, db);
        db.execSQL(SQL_CREATE_TABLE_ACCOUNT);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_ENTRY);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_INTERVAL);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_INTERVAL_CHANGE);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_INTERVAL_ENTRY);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_TEMPLATE);
        db.execSQL(SQL_CREATE_TABLE_BOOKING_TEMPLATE_KEYWORD);
        db.execSQL(SQL_CREATE_TABLE_CATEGORY);
        mOpenHelperCallbacks.onPostCreate(mContext, db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if (!db.isReadOnly()) {
            setForeignKeyConstraintsEnabled(db);
        }
        mOpenHelperCallbacks.onOpen(mContext, db);
    }

    private void setForeignKeyConstraintsEnabled(SQLiteDatabase db) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            setForeignKeyConstraintsEnabledPreJellyBean(db);
        } else {
            setForeignKeyConstraintsEnabledPostJellyBean(db);
        }
    }

    private void setForeignKeyConstraintsEnabledPreJellyBean(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void setForeignKeyConstraintsEnabledPostJellyBean(SQLiteDatabase db) {
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        mOpenHelperCallbacks.onUpgrade(mContext, db, oldVersion, newVersion);
    }
}
