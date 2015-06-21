package de.nenick.quacc.testdata;

import org.chalup.microorm.annotations.Column;

import de.nenick.quacc.database.provider.category.CategoryColumns;
import de.nenick.quacc.testdata.base.BaseModel;

public class Category extends BaseModel {

    @Column(CategoryColumns.NAME)
    String NAME;

    @Column(CategoryColumns.SECTION)
    String SECTION;

    @Column(CategoryColumns.INTERVAL)
    String INTERVAL;

    @Column(CategoryColumns.TYPE)
    String TYPE;

    @Column(CategoryColumns.LEVEL)
    int LEVEL;
}
