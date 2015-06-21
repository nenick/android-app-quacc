package de.nenick.quacc.testdata.base;

import android.provider.BaseColumns;

import org.chalup.microorm.annotations.Column;

public class BaseModel {
    @Column(value = BaseColumns._ID, readonly = true)
    public long _id;
}
